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
public class Park {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    private Integer rank;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Attraction> attractions;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Manager> managers;
}
