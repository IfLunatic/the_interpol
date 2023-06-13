package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.Nationality;

public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
}