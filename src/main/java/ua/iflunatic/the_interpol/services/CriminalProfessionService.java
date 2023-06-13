package ua.iflunatic.the_interpol.services;

import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;
import ua.iflunatic.the_interpol.repositories.CriminalProfessionRepository;


@Service
public class CriminalProfessionService {

    private CriminalProfessionRepository criminalProfessionRepository;

    public CriminalProfessionService(CriminalProfessionRepository criminalProfessionRepository) {
        this.criminalProfessionRepository = criminalProfessionRepository;
    }

    public Object getCriminalProfessions() {
        return criminalProfessionRepository.findAll();
    }

    public void save(CriminalProfession criminalProfession) {
        criminalProfessionRepository.save(criminalProfession);
    }
}
