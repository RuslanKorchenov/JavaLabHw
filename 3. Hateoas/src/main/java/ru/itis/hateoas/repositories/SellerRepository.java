package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>{
}
