package cholog.supp.api.studygroup.dto.response;

import cholog.supp.common.jwt.VerifyToken;
import cholog.supp.db.member.MemberType;

public record JoinGroupResponse(
    Long studyGroupId,
    MemberType memberType,
    String organization,
    String studyGroupName
) {

    public JoinGroupResponse(VerifyToken verifyToken, String studyGroupName) {
        this(
            verifyToken.studyGroupId(),
            verifyToken.memberType(),
            verifyToken.organization(),
            studyGroupName
        );
    }
}
