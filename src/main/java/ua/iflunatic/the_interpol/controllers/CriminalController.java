package ua.iflunatic.the_interpol.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.services.*;

import java.util.List;


@Controller
@RequestMapping("/criminals")
@AllArgsConstructor
public class CriminalController {

    private final CriminalService criminalService;
    private final GroupService groupService;
    private final LanguageService languageService;
    private final CriminalProfessionService criminalProfessionService;
    private final NationalityService nationalityService;

    @GetMapping
    public String showCriminals(Model model) {
        model.addAttribute("criminals", criminalService.getCriminals());
        return "criminal/criminals";
    }

    @GetMapping("/new")
    public String newCriminal(Model model) {
        model.addAttribute("criminal", new Criminal());
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("languages", languageService.getLanguages());
        model.addAttribute("professions", criminalProfessionService.getCriminalProfessions());
        model.addAttribute("nationalities", nationalityService.getNationalities());
        return "criminal/createCriminal";
    }

    @PostMapping
    public String create(@ModelAttribute("criminal")  Criminal criminal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "criminal/createCriminal";
        }
        criminalService.save(criminal);
        return "redirect:/criminals";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("criminal", criminalService.findOne(id));
        return "criminal/showTheSelectedOffender";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("criminal", criminalService.findOne(id));
        model.addAttribute("groups", groupService.getGroups());
        model.addAttribute("languages", languageService.getLanguages());
        model.addAttribute("professions", criminalProfessionService.getCriminalProfessions());
        model.addAttribute("nationalities", nationalityService.getNationalities());
        return "criminal/editCriminal";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("criminal") Criminal criminal, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "criminal/editCriminal";

        criminal.isArchived();
        criminalService.update(id, criminal);

        return "redirect:/criminals";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        criminalService.delete(id);
        return "redirect:/criminals";
    }

    @GetMapping("/filter")
    public String showFilterPage() {
        return "criminal/filter";
    }

    @PostMapping("/filter")
    public String filterCriminals(@RequestParam(required = false) String surname,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String nickname,
                                  @RequestParam(required = false) Integer height,
                                  @RequestParam(required = false) String hairColour,
                                  @RequestParam(required = false) String eyeColour,
                                  @RequestParam(required = false) String specialFeatures,
                                  @RequestParam(required = false) String placeOfOrigin,
                                  @RequestParam(required = false) String dateOfBirth,
                                  @RequestParam(required = false) String lastPlaceOfResidence,
                                  @RequestParam(required = false) String lastCase,
                                  @RequestParam(required = false) Boolean includeArchived,
                                  @RequestParam(required = false) Long groupId,
                                  @RequestParam(required = false) Long professionId,
                                  @RequestParam(required = false) Long languageId,
                                  @RequestParam(required = false) String nationality,
                                  Model model) {
        List<Criminal> filteredCriminals = criminalService.filterCriminals(surname, name, nickname, height, hairColour, eyeColour, specialFeatures, placeOfOrigin, dateOfBirth, lastPlaceOfResidence, lastCase, includeArchived, groupId, professionId, languageId, nationality, includeArchived);
        model.addAttribute("filteredCriminals", filteredCriminals);
        return "criminal/filteredCriminals";
    }
}