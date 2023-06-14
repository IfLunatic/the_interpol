package ua.iflunatic.the_interpol.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.iflunatic.the_interpol.entities.CriminalProfession;
import ua.iflunatic.the_interpol.services.CriminalProfessionService;

@Controller
@AllArgsConstructor
@RequestMapping("/criminalProfession")
public class CriminalProfessionController {
    private final CriminalProfessionService criminalProfessionService;

    @GetMapping("/showAll")
    public String showCriminalsProfessions(Model model) {
        model.addAttribute("criminalsProfession", criminalProfessionService.getCriminalProfessions());
        return "criminalProfession/showAll";
    }

    @GetMapping("/new")
    public String newCriminalProfession(Model model) {
        model.addAttribute("criminalProfession", new CriminalProfession());
        return "criminalProfession/newProfession";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("criminalProfession") CriminalProfession criminalProfession, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "criminalProfession/newProfession";
        }
        criminalProfessionService.save(criminalProfession);
        return "redirect:/criminalProfession/showAll";
    }
}
