package cholog.supp.db.post;

import cholog.supp.api.post.dto.ModifyPost;
import cholog.supp.db.AuditEntity;
import cholog.supp.db.member.Member;
import cholog.supp.db.study.StudyGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "POST")
@NoArgsConstructor
public class Post extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "study_group_id", nullable = false)
    private StudyGroup study;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    public Post(Member member, StudyGroup study, String title, String description) {
        this.member = member;
        this.study = study;
        this.title = title;
        this.description = description;
    }

    public void modifyPost(ModifyPost modifyPost) {
        this.title = modifyPost.title();
        this.description = modifyPost.description();
    }
}
