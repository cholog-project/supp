package cholog.supp.db.post;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.study.id = :studyId order by p.createdDate desc")
    List<Post> findAllByStudyIdDesc(Long studyId);
}
