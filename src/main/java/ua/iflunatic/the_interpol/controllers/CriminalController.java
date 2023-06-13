package ua.iflunatic.the_interpol.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.services.*;

@Controller
@AllArgsConstructor
public class CriminalController {

    private final CriminalService criminalService;
    private final GroupService groupService;
    private final LanguageService languageService;
    private final CriminalProfessionService criminalProfessionService;
    private final NationalityService nationalityService;


    @GetMapping("/criminals")
    private String showCriminals(Model model) {
        model.addAttribute("criminals", criminalService.getCriminals());
        return "/criminals";
    }

    @GetMapping("/new")
    public String newCriminal(Model model) {
        model.addAttribute("criminal", new Criminal());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("languages", languageService.getLanguages());
        model.addAttribute("professions", criminalProfessionService.getCriminalProfessions());
        model.addAttribute("nationalities", nationalityService.getNationalities());
        return "createCriminal";
    }

    @PostMapping("/criminals")
    public String create(@ModelAttribute("criminal") Criminal criminal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createCriminal";
        }
        criminalService.save(criminal);
        return "redirect:/criminals";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("criminal", criminalService.findOne(id));
        return "showTheSelectedOffender";
    }

    @GetMapping("/{id}/editCriminal")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("criminal", new Criminal());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("languages", languageService.getLanguages());
        model.addAttribute("professions", criminalProfessionService.getCriminalProfessions());
        model.addAttribute("nationalities", nationalityService.getNationalities());
        return "editCriminal";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("criminal") @Valid Criminal criminal, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "editCriminal";

        criminalService.update(id, criminal);
        return "redirect:/criminals";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        criminalService.delete(id);
        return "redirect:criminals";
    }

}
