package ua.iflunatic.the_interpol.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Entity
@Table(name = "criminal")
@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Surname should not be empty")
    @NotNull
    private String surname;

    @NotNull
    @NotBlank(message = "Name should not be empty")
    private String name;

    @NotNull
    @NotBlank(message = "Nickname should not be empty")
    private String nickname;

    @NotNull
    @Min(value = 1, message = "Height should be greater than 0")
    private Integer height;

    @NotNull
    @NotBlank(message = "Hair colour should not be empty")
    private String hairColour;

    @NotNull
    @NotBlank(message = "Eye colour should not be empty")
    private String eyeColour;

    private String specialFeatures;

    @NotNull
    @NotBlank(message = "Place of origin should not be empty")
    private String placeOfOrigin;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата повинна бути записана у форматі (рік-місяць-день)")
    private String dateOfBirth;

    @NotNull
    @NotBlank(message = "Last place of residence should not be empty")
    private String lastPlaceOfResidence;

    private String lastCase;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private CriminalProfession profession;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @PrePersist
    @PreUpdate
    private void validateFields() {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname should not be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name should not be empty");
        }
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname should not be empty");
        }
        if (height == null || height <= 0) {
            throw new IllegalArgumentException("Height should be greater than 0");
        }
        if (hairColour == null || hairColour.trim().isEmpty()) {
            throw new IllegalArgumentException("Hair colour should not be empty");
        }
        if (eyeColour == null || eyeColour.trim().isEmpty()) {
            throw new IllegalArgumentException("Eye colour should not be empty");
        }
        if (placeOfOrigin == null || placeOfOrigin.trim().isEmpty()) {
            throw new IllegalArgumentException("Place of origin should not be empty");
        }
        if (dateOfBirth == null || dateOfBirth.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of birth should not be empty");
        }
        if (lastPlaceOfResidence == null || lastPlaceOfResidence.trim().isEmpty()) {
            throw new IllegalArgumentException("Last place of residence should not be empty");
        }
    }

    @Override
    public String toString() {
        return "Criminal{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", height=" + height +
                ", hairColour='" + hairColour + '\'' +
                ", eyeColour='" + eyeColour + '\'' +
                ", specialFeatures='" + specialFeatures + '\'' +
                ", nationality=" + (nationality != null ? nationality.getName() : null) +
                ", placeOfOrigin='" + placeOfOrigin + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", lastPlaceOfResidence='" + lastPlaceOfResidence + '\'' +
                ", lastCase='" + lastCase + '\'' +
                ", group=" + (group != null ? group.getGroupName() : null) +
                ", profession=" + (profession != null ? profession.getName() : null) +
                ", language=" + (language != null ? language.getName() : null) +
                '}';
    }

}
