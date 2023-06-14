package ua.iflunatic.the_interpol.services;

import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;
import ua.iflunatic.the_interpol.repositories.CriminalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CriminalService {

    private CriminalRepository criminalRepository;
    public CriminalService(CriminalRepository repository) {
        this.criminalRepository = repository;
    }

    public List<Criminal> getCriminals() {
        return criminalRepository.findAll();
    }

    public void save(Criminal criminal) {
        criminalRepository.save(criminal);
    }

    public Criminal findOne(int id) {
        Optional<Criminal> foundCriminal = criminalRepository.findById(id);
        return foundCriminal.orElse(null);
    }

    public void update(int id, Criminal updateCriminal) {
        updateCriminal.setId(id);
        criminalRepository.save(updateCriminal);
    }

    public void delete(int id) {
        criminalRepository.deleteById(id);
    }

    public List<Criminal> getCriminalsByProfession(CriminalProfession criminalProfession) {
        return criminalRepository.findByProfession(criminalProfession);
    }
}
