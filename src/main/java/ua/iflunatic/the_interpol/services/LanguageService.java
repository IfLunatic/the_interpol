package ua.iflunatic.the_interpol.services;

import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Language;
import ua.iflunatic.the_interpol.repositories.LanguageRepository;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll().stream().toList();
    }

    public Language getLanguageById(Integer languageId) {
        return languageRepository.findById(languageId).orElse(null);
    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }
}
