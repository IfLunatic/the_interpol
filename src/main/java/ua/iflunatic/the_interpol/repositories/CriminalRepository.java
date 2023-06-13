package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.Criminal;

public interface CriminalRepository extends JpaRepository<Criminal, Integer> {
}
