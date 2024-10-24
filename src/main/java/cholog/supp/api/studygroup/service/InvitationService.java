package cholog.supp.api.studygroup.service;

import cholog.supp.api.studygroup.dto.request.InvitationRequest;
import cholog.supp.api.studygroup.dto.response.InvitationResponse;
import cholog.supp.common.jwt.JwtUtils;
import cholog.supp.db.study.StudyGroup;
import cholog.supp.db.study.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private static final String URL = "/api/v1/groups/join?token=";
    private final JwtUtils jwtUtils;
    private final StudyGroupRepository studyGroupRepository;

    public InvitationResponse createInvitationLink(InvitationRequest invitationRequest) {
        StudyGroup studyGroup = studyGroupRepository.findById(invitationRequest.studyId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        String token = jwtUtils.createToken(studyGroup, invitationRequest.memberType());
        return new InvitationResponse(URL + token);
    }
}
