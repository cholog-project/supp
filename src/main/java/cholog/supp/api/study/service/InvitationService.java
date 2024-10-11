package cholog.supp.api.study.service;

import cholog.supp.api.study.dto.request.InvitationRequest;
import cholog.supp.common.jwt.JwtUtils;
import cholog.supp.db.study.StudyGroup;
import cholog.supp.db.study.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final JwtUtils jwtUtils;
    private final String URL = "/api/v1/group/join?token=";
    private final StudyGroupRepository studyGroupRepository;

    public String createInvitation(InvitationRequest invitationRequest) {
        StudyGroup studyGroup = studyGroupRepository.findById(invitationRequest.studyId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        String token = jwtUtils.createToken(studyGroup, invitationRequest.memberType());
        return URL + token;
    }
}
