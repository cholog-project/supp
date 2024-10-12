package cholog.supp.db.study;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "STUDY_GROUP")
@NoArgsConstructor
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_group_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "study_group_name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "organization")
    private String organization;

    public StudyGroup(String name, String organization) {
        this.name = name;
        this.organization = organization;
    }
}
