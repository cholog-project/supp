package cholog.supp.common.jwt;

import cholog.supp.db.member.MemberType;

public record VerifyToken(
    Long studyGroupId,
    MemberType memberType,
    String organization
) {

}
