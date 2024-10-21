package cholog.supp.api.studygroup.service;

import cholog.supp.api.studygroup.dto.request.CreateStudyGroupRequest;
import cholog.supp.api.studygroup.dto.response.EachGroupResponse;
import cholog.supp.api.studygroup.dto.response.JoinGroupResponse;
import cholog.supp.api.studygroup.dto.response.StudyGroupResponse;
import cholog.supp.common.jwt.JwtUtils;
import cholog.supp.common.jwt.VerifyToken;
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
    private final JwtUtils jwtUtils;

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

    @Transactional(readOnly = true)
    public EachGroupResponse getEachGroup(Member member, Long groupId) {
        StudyGroup studyGroup = studyGroupRepository.findById(groupId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        long peopleCount = memberStudyMapRepository.countByStudyGroupId(groupId);
        MemberStudyMap memberStudyMap = memberStudyMapRepository.findByStudyGroupIdAndMemberId(
                groupId, member.getId())
            .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
        MemberType memberType = memberStudyMap.getMemberCategory().getMemberType();
        return new EachGroupResponse(studyGroup, peopleCount, memberType);
    }

    public JoinGroupResponse joinGroup(Member member, String token) {
        VerifyToken verifyToken = jwtUtils.verifyToken(token);
        MemberCategory memberCategory = memberCategoryRepository.save(
            new MemberCategory(member, verifyToken.memberType()));
        StudyGroup studyGroup = studyGroupRepository.findById(verifyToken.studyGroupId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        if (memberStudyMapRepository.findByStudyGroupIdAndMemberId(studyGroup.getId(),
            member.getId()).isPresent()) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
        memberStudyMapRepository.save(new MemberStudyMap(member, memberCategory, studyGroup));
        return new JoinGroupResponse(verifyToken, studyGroup.getName());
    }
}
