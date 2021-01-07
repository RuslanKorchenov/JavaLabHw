package ru.itis.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Attr;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Manager {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private Integer salary;

    @ManyToOne(fetch = FetchType.LAZY)
    private Park park;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Seller> sellers;
}
