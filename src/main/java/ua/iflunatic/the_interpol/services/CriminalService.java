package ua.iflunatic.the_interpol.services;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;
import ua.iflunatic.the_interpol.repositories.CriminalRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Criminal> filterCriminals(String surname, String name, String nickname, @Min(value = 0, message = "The increase cannot be less than 0") @NotNull Integer height,
                                          String hairColour, String eyeColour, String specialFeatures,
                                          String placeOfOrigin, String dateOfBirth, String lastPlaceOfResidence,
                                          String lastCase, boolean isArchived, Integer groupId, Integer professionId,
                                          Integer languageId, Integer nationalityId) {
        List<Criminal> allCriminals = criminalRepository.findAll();

        return allCriminals.stream()
                .filter(criminal -> (surname == null || criminal.getSurname().equals(surname)))
                .filter(criminal -> (name == null || criminal.getName().equals(name)))
                .filter(criminal -> (nickname == null || criminal.getNickname().equals(nickname)))
                .filter(criminal -> Objects.isNull(height) || criminal.getHeight().equals(height))
                .filter(criminal -> (hairColour == null || criminal.getHairColour().equals(hairColour)))
                .filter(criminal -> (eyeColour == null || criminal.getEyeColour().equals(eyeColour)))
                .filter(criminal -> (specialFeatures == null || criminal.getSpecialFeatures().equals(specialFeatures)))
                .filter(criminal -> (placeOfOrigin == null || criminal.getPlaceOfOrigin().equals(placeOfOrigin)))
                .filter(criminal -> (dateOfBirth == null || criminal.getDateOfBirth().equals(dateOfBirth)))
                .filter(criminal -> (lastPlaceOfResidence == null || criminal.getLastPlaceOfResidence().equals(lastPlaceOfResidence)))
                .filter(criminal -> (lastCase == null || criminal.getLastCase().equals(lastCase)))
                .filter(criminal -> Objects.isNull(groupId) || Objects.equals(criminal.getGroup().getId(), groupId))
                .filter(criminal -> Objects.isNull(professionId) || Objects.equals(criminal.getProfession().getId(), professionId))
                .filter(criminal -> Objects.isNull(languageId) || Objects.equals(criminal.getLanguage().getId(), languageId))
                .filter(criminal -> Objects.isNull(nationalityId) || Objects.equals(criminal.getNationality().getId(), nationalityId))
                .filter(criminal -> criminal.isArchived() == isArchived)
                .collect(Collectors.toList());
    }
}