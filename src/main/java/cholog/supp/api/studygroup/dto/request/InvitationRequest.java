package cholog.supp.api.studygroup.dto.request;

import cholog.supp.db.member.MemberType;

public record InvitationRequest(
    MemberType memberType,
    Long studyId
) {

}
