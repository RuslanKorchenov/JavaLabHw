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
public class Shop {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShopType shopType;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne(fetch = FetchType.LAZY)
    private Seller seller;

    public enum ShopType {
        WATER, FOOD, SOUVENIRS
    }
}
