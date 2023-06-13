package ua.iflunatic.the_interpol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iflunatic.the_interpol.entities.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
