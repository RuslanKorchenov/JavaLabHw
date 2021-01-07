package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Seller {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Integer salary;

    @Enumerated(EnumType.STRING)
    private SellerType sellerType;

    @OneToOne(fetch = FetchType.LAZY)
    private Shop shop;
    @OneToOne(fetch = FetchType.LAZY)
    private Attraction attraction;
    @ManyToOne(fetch = FetchType.LAZY)
    private Manager manager;

    public enum SellerType {
        ATTRACTION, SHOP
    }

}
