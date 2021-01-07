package ru.itis.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
