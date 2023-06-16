package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
            "AND (:specialFeatures IS NULL OR c.specialFeatures = :specialFeatures) " +
            "AND (:placeOfOrigin IS NULL OR c.placeOfOrigin = :placeOfOrigin) " +
            "AND (:dateOfBirth IS NULL OR c.dateOfBirth = :dateOfBirth) " +
            "AND (:lastPlaceOfResidence IS NULL OR c.lastPlaceOfResidence = :lastPlaceOfResidence) " +
            "AND (:lastCase IS NULL OR c.lastCase = :lastCase) " +
            "AND (:archived IS NULL OR c.archived = :archived) " +
            "AND (:groupId IS NULL OR c.group.id = :groupId) " +
            "AND (:professionId IS NULL OR c.profession.id = :professionId) " +
            "AND (:languageId IS NULL OR c.language.id = :languageId) " +
            "AND (:nationalityId IS NULL OR c.nationality.id = :nationalityId) " +
            "AND (:includeArchived IS NULL OR c.archived = :includeArchived)")
    List<Criminal> filterCriminals(@Param("surname") String surname, @Param("name") String name, @Param("nickname") String nickname, @Param("height") Integer height,
                                   @Param("hairColour") String hairColour, @Param("eyeColour") String eyeColour, @Param("specialFeatures") String specialFeatures,
                                   @Param("placeOfOrigin") String placeOfOrigin, @Param("dateOfBirth") String dateOfBirth, @Param("lastPlaceOfResidence") String lastPlaceOfResidence,
                                   @Param("lastCase") String lastCase, @Param("archived") Boolean archived, @Param("groupId") Integer groupId,
                                   @Param("professionId") Integer professionId, @Param("languageId") Integer languageId,
                                   @Param("nationalityId") Integer nationalityId);
}
