package ua.iflunatic.the_interpol.services;

import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Nationality;
import ua.iflunatic.the_interpol.repositories.NationalityRepository;

import java.util.List;

@Service
public class NationalityService {
    private final NationalityRepository nationalityRepository;

    public NationalityService(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    public List<Nationality> getNationalities() {
        return nationalityRepository.findAll();
    }
}
