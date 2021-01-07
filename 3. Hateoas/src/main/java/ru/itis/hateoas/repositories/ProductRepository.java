package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
