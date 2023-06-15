package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;

import java.util.List;

public interface CriminalRepository extends JpaRepository<Criminal, Integer> {
    List<Criminal> findByProfession(CriminalProfession criminalProfession);

    @Query("SELECT c FROM Criminal c " +
            "WHERE (:surname IS NULL OR c.surname = :surname) " +
            "AND (:name IS NULL OR c.name = :name) " +
            "AND (:nickname IS NULL OR c.nickname = :nickname) " +
            "AND (:height IS NULL OR c.height = :height) " +
            "AND (:hairColour IS NULL OR c.hairColour = :hairColour) " +
            "AND (:eyeColour IS NULL OR c.eyeColour = :eyeColour) " +
            "AND (:placeOfOrigin IS NULL OR c.placeOfOrigin = :placeOfOrigin) " +
            "AND (:dateOfBirth IS NULL OR c.dateOfBirth = :dateOfBirth) " +
            "AND (:lastPlaceOfResidence IS NULL OR c.lastPlaceOfResidence = :lastPlaceOfResidence) " +
            "AND (:lastCase IS NULL OR c.lastCase = :lastCase) " +
            "AND (:archived IS NULL OR c.archived = :archived)")
    List<Criminal> filterCriminals(String surname, String name, String nickname, Integer height, String hairColour, String eyeColour, String placeOfOrigin, String dateOfBirth, String lastPlaceOfResidence, String lastCase, String aCase, Boolean archived, Boolean includeArchived, Long groupId, Long professionId, Long languageId, String nationality);

}
