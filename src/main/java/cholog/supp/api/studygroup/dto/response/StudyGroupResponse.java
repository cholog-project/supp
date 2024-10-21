package cholog.supp.api.studygroup.dto.response;

import cholog.supp.db.study.StudyGroup;

public record StudyGroupResponse(
    Long studyId,
    String studyName,
    String organization,
    Long peopleCount
) {

    public StudyGroupResponse(StudyGroup studyGroup, Long peopleCount) {
        this(
            studyGroup.getId(),
            studyGroup.getName(),
            studyGroup.getOrganization(),
            peopleCount
        );
    }
}
