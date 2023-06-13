package ua.iflunatic.the_interpol.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nationality")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "nationality", orphanRemoval = true)
    private List<Criminal> criminals = new ArrayList<>();

    @Override
    public String toString() {
        return getName();
    }
}
