package cholog.supp.api.member.dto.response;

import cholog.supp.db.member.MemberType;

public record MemberInfoResponse(
    Long id,
    String email,
    MemberType memberType
) {

}
