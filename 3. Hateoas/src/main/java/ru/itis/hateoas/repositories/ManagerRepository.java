package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
