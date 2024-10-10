package cholog.supp.db.member;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStudyMapRepository extends JpaRepository<MemberStudyMap, Long> {

    List<MemberStudyMap> findByMemberId(Long memberId);

    long countByStudyGroupId(Long studyGroupId);

    MemberStudyMap findByStudyGroupIdAndMemberId(Long studyGroupId, Long memberId);
}
