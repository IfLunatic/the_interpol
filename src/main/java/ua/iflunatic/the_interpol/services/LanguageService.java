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
}
