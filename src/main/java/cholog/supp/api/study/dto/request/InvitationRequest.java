package cholog.supp.api.study.dto.request;

import cholog.supp.db.member.MemberType;

public record InvitationRequest(
    MemberType memberType,
    Long studyId
) {

}
