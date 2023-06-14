package ua.iflunatic.the_interpol.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.iflunatic.the_interpol.entities.Group;
import ua.iflunatic.the_interpol.services.GroupService;

@Controller
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/showAll")
    public String showGroups(Model model) {
        model.addAttribute("groups", groupService.getGroups());
        return "group/showAll";
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
}