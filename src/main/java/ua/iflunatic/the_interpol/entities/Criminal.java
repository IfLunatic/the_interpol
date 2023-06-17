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

    private String surname;

    private String name;


    private String nickname;

    private Integer height;

    private String hairColour;

    private String eyeColour;

    private String specialFeatures;

    private String placeOfOrigin;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата повинна бути записана у форматі (рік-місяць-день)")
    private String dateOfBirth;

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
