package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.CriminalProfession;

public interface CriminalProfessionRepository extends JpaRepository<CriminalProfession, Integer> {
}
