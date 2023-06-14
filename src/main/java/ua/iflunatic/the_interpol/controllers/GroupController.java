package ua.iflunatic.the_interpol.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Group;
import ua.iflunatic.the_interpol.services.GroupService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/showAll")
    public String showGroups(Model model) {
        List<Group> groups = groupService.getGroups();
        model.addAttribute("groups", groups);
        return "group/showAll";
    }

    @GetMapping("/{groupId}")
    public String showGroupMembers(@PathVariable("groupId") Integer groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        model.addAttribute("group", group);
        model.addAttribute("members", group.getCriminals());
        return "group/showGroupMembers";
    }

    @GetMapping("/new")
    public String newGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/newGroup";
    }

    @PostMapping("/newGroup")
    public String create(@ModelAttribute("group") Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "group/newGroup";
        }
        groupService.save(group);
        return "redirect:/group/showAll";
    }

    @GetMapping("/{groupId}/edit")
    public String editGroupForm(@PathVariable("groupId") Integer groupId, Model model) {
        Group group = groupService.getGroupById(groupId);
        model.addAttribute("group", group);
        return "group/editGroup";
    }

    @PatchMapping("/{groupId}")
    public String updateGroup(@PathVariable("groupId") Integer groupId, @ModelAttribute("group") Group updatedGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "group/editGroup";
        }
        Group group = groupService.getGroupById(groupId);
        group.setGroupName(updatedGroup.getGroupName());
        groupService.save(group);
        return "redirect:/group/showAll";
    }
}