package ua.iflunatic.the_interpol.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Nationality;
import ua.iflunatic.the_interpol.services.NationalityService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/nationality")
public class NationalityController {
    private final NationalityService nationalityService;

    @GetMapping("/showAll")
    public String showNationalities(Model model) {
        List<Nationality> nationalities = nationalityService.getAllNationalities();
        model.addAttribute("nationalities", nationalities);
        return "nationality/showAll";
    }

    @GetMapping("/{nationalityId}")
    public String showSelectedNationality(@PathVariable("nationalityId") Integer nationalityId, Model model) {
        Nationality nationality = nationalityService.getNationalityById(nationalityId);
        model.addAttribute("nationality", nationality);
        return "nationality/showSelectedNationality";
    }

    @GetMapping("/new")
    public String newNationality(Model model) {
        model.addAttribute("nationality", new Nationality());
        return "nationality/newNationality";
    }

    @PostMapping("/new")
    public String createNationality(@ModelAttribute("nationality") @Valid Nationality nationality, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "nationality/newNationality";
        }
        nationalityService.save(nationality);
        return "redirect:/nationality/showAll";
    }

    @GetMapping("/{nationalityId}/edit")
    public String editNationalityForm(@PathVariable("nationalityId") Integer nationalityId, Model model) {
        Nationality nationality = nationalityService.getNationalityById(nationalityId);
        model.addAttribute("nationality", nationality);
        return "nationality/editNationality";
    }

    @PatchMapping("/{nationalityId}")
    public String updateNationality(@PathVariable("nationalityId") Integer nationalityId, @ModelAttribute("nationality") @Valid Nationality updatedNationality, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "nationality/editNationality";
        }
        Nationality nationality = nationalityService.getNationalityById(nationalityId);
        nationality.setName(updatedNationality.getName());
        nationalityService.save(nationality);
        return "redirect:/nationality/showAll";
    }
}