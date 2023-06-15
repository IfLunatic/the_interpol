package ua.iflunatic.the_interpol.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;
import ua.iflunatic.the_interpol.services.CriminalProfessionService;
import ua.iflunatic.the_interpol.services.CriminalService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/criminalProfession")
public class CriminalProfessionController {
    private final CriminalProfessionService criminalProfessionService;
    private final CriminalService criminalService;

    @GetMapping("/showAll")
    public String showCriminalsProfessions(Model model) {
        model.addAttribute("criminalsProfession", criminalProfessionService.getCriminalProfessions());
        return "criminalProfession/showAll";
    }

    @GetMapping("/{professionId}")
    public String showCriminalsByProfession(@PathVariable("professionId") Integer professionId, Model model) {
        CriminalProfession criminalProfession = criminalProfessionService.getCriminalProfessionsById(professionId);
        List<Criminal> criminalsByProfession = criminalService.getCriminalsByProfession(criminalProfession);
        model.addAttribute("profession", criminalProfession);
        model.addAttribute("criminals", criminalsByProfession);
        return "criminalProfession/showCriminalsByProfession";
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

    @GetMapping("/{professionId}/edit")
    public String editProfessionForm(@PathVariable("professionId") Integer professionId, Model model) {
        CriminalProfession profession = criminalProfessionService.getCriminalProfessionsById(professionId);
        model.addAttribute("criminalProfession", profession);
        return "criminalProfession/editProfession";
    }

    @PatchMapping("/{professionId}/edit")
    public String updateProfession(@PathVariable("professionId") Integer professionId, @ModelAttribute("criminalProfession") CriminalProfession updatedProfession, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "criminalProfession/editProfession";
        }
        CriminalProfession profession = criminalProfessionService.getCriminalProfessionsById(professionId);
        profession.setName(updatedProfession.getName());
        criminalProfessionService.save(profession);
        return "redirect:/criminalProfession/showAll";
    }
}
