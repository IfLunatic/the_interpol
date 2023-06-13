package ua.iflunatic.the_interpol.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.iflunatic.the_interpol.entities.Group;
import ua.iflunatic.the_interpol.services.GroupService;

@Controller
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/group/showAll")
    private String showGroup(Model model) {
        model.addAttribute("group", groupService.getGroups());
        return "/group/showAll";
    }

    @GetMapping("/group/new")
    public String newGroup(Model model) {
        model.addAttribute("group", groupService.getGroups());
        return "/group/newGroup";
    }

    @PostMapping("/group/newGroup")
    public String create(@ModelAttribute("criminal") Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/group/newGroup";
        }
        groupService.save(group);
        return "redirect:/group/showAll";
    }
}
