package ua.iflunatic.the_interpol.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Table(name = "criminal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
    @NotEmpty
    private String surname;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Nickname should be between 2 and 30 characters")
    private String nickname;

    @Min(value = 0, message = "The increase cannot be less than 0")
    @NotNull
    private Integer height;

    @Size(min = 2, max = 30, message = "Hair colour should be between 2 and 30 characters")
    @NotEmpty
    private String hairColour;

    @Size(min = 2, max = 30, message = "Eye colour should be between 2 and 30 characters")
    @NotEmpty
    private String eyeColour;

    private String specialFeatures;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Place of origin should be between 2 and 30 characters")
    private String placeOfOrigin;

    @NotEmpty
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата повинна бути записана у форматі (рік-місяць-день)")
    private String dateOfBirth;

    @NotEmpty
    private String lastPlaceOfResidence;

    @NotEmpty
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
