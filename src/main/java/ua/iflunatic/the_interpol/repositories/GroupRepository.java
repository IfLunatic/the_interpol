package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
