package ua.iflunatic.the_interpol.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "criminal_profession")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriminalProfession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "profession", orphanRemoval = true)
    private List<Criminal> criminals = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}