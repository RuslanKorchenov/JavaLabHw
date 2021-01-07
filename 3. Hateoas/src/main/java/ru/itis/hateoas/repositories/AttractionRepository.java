package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Attraction;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
}
