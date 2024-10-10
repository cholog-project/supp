package cholog.supp.db.member;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyMapRepository extends JpaRepository<MemberStudyMap, Long> {

    List<MemberStudyMap> findAllByMemberId(Long memberId);

    long countByStudyGroupId(Long studyGroupId);

    Optional<MemberStudyMap> findByStudyGroupIdAndMemberId(Long studyGroupId, Long memberId);
}
