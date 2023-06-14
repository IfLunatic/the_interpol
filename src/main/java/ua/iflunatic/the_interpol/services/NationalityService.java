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


    public List<Nationality> getAllNationalities() {
        return nationalityRepository.findAll();
    }

    public Nationality getNationalityById(Integer nationalityId) {
        return nationalityRepository.findById(nationalityId).orElse(null);
    }

    public void save(Nationality nationality) {
        nationalityRepository.save(nationality);
    }
}
