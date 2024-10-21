package cholog.supp.api.studygroup.dto.response;

import cholog.supp.db.member.MemberType;
import cholog.supp.db.study.StudyGroup;

public record EachGroupResponse(
    Long studyId,
    String studyName,
    String organization,
    Long peopleCount,
    MemberType memberType
) {

    public EachGroupResponse(StudyGroup studyGroup, Long peopleCount, MemberType memberType) {
        this(
            studyGroup.getId(),
            studyGroup.getName(),
            studyGroup.getOrganization(),
            peopleCount,
            memberType
        );
    }
}
