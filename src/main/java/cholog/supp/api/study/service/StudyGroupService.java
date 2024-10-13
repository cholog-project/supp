package cholog.supp.api.study.service;

import cholog.supp.api.study.dto.request.CreateStudyGroupRequest;
import cholog.supp.api.study.dto.response.StudyGroupResponse;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberCategory;
import cholog.supp.db.member.MemberCategoryRepository;
import cholog.supp.db.member.MemberStudyMap;
import cholog.supp.db.member.MemberStudyMapRepository;
import cholog.supp.db.member.MemberType;
import cholog.supp.db.study.StudyGroup;
import cholog.supp.db.study.StudyGroupRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final MemberCategoryRepository memberCategoryRepository;
    private final MemberStudyMapRepository memberStudyMapRepository;

    public Long createStudyGroup(CreateStudyGroupRequest request, Member member) {
        StudyGroup studyGroup = studyGroupRepository.save(
            new StudyGroup(request.studyName(), request.organization()));
        MemberCategory memberCategory = memberCategoryRepository.save(
            new MemberCategory(member, MemberType.NODE));
        memberStudyMapRepository.save(new MemberStudyMap(member, memberCategory, studyGroup));
        return studyGroup.getId();
    }

    @Transactional(readOnly = true)
    public List<StudyGroupResponse> getGroup(Member member) {
        List<MemberStudyMap> memberStudyMaps = memberStudyMapRepository.findAllByMemberId(
            member.getId());
        return memberStudyMaps.stream()
            .map(MemberStudyMap::getStudyGroup)
            .map(it -> {
                long peopleCount = memberStudyMapRepository.countByStudyGroupId(it.getId());
                return new StudyGroupResponse(it.getId(), it.getName(), it.getOrganization(),
                    peopleCount);
            }).toList();
    }
}
