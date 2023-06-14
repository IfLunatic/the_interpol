package ua.iflunatic.the_interpol.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Language;
import ua.iflunatic.the_interpol.services.LanguageService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/language")
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping("/showAll")
    public String showLanguages(Model model) {
        List<Language> languages = languageService.getAllLanguages();
        model.addAttribute("languages", languages);
        return "language/showAll";
    }

    @GetMapping("/{languageId}")
    public String showSelectedLanguage(@PathVariable("languageId") Integer languageId, Model model) {
        Language language = languageService.getLanguageById(languageId);
        model.addAttribute("language", language);
        return "language/showSelectedLanguage";
    }

    @GetMapping("/new")
    public String newLanguage(Model model) {
        model.addAttribute("language", new Language());
        return "language/newLanguage";
    }

    @PostMapping("/new")
    public String createLanguage(@ModelAttribute("language") @Valid Language language, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "language/newLanguage";
        }
        languageService.save(language);
        return "redirect:/language/showAll";
    }

    @GetMapping("/{languageId}/edit")
    public String editLanguageForm(@PathVariable("languageId") Integer languageId, Model model) {
        Language language = languageService.getLanguageById(languageId);
        model.addAttribute("language", language);
        return "language/editLanguage";
    }

    @PatchMapping("/{languageId}")
    public String updateLanguage(@PathVariable("languageId") Integer languageId, @ModelAttribute("language") @Valid Language updatedLanguage, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "language/editLanguage";
        }
        Language language = languageService.getLanguageById(languageId);
        language.setName(updatedLanguage.getName());
        languageService.save(language);
        return "redirect:/language/showAll";
    }
}