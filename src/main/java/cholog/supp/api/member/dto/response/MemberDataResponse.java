package cholog.supp.api.member.dto.response;

import cholog.supp.db.member.MemberType;

public record MemberDataResponse(
    Long memberId,
    String memberEmail,
    MemberType memberType
) {

}
