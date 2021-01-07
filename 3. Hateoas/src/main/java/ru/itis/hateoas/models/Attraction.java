package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attraction {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer countOfSeats;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Park park;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    @OneToOne
    private Seller seller;

    public enum Status {
        AVAILABLE, BROKEN, NO_TICKETS
    }

    public void updateStatus(int status) {
        if (status == 1)
            this.status = Attraction.Status.AVAILABLE;
        else if (status == 2)
            this.status = Attraction.Status.NO_TICKETS;
        else if (status == 3)
            this.status = Attraction.Status.BROKEN;
        else
            throw new IllegalArgumentException("Unknown status");
    }
}
