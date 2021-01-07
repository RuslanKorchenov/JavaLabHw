package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Park;

public interface ParkRepository extends JpaRepository<Park, Long> {
}
