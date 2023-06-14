package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.Criminal;
import ua.iflunatic.the_interpol.entities.CriminalProfession;

import java.util.List;

public interface CriminalRepository extends JpaRepository<Criminal, Integer> {
    List<Criminal> findByProfession(CriminalProfession criminalProfession);
}
