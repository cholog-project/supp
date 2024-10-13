package cholog.supp.api.study.dto.response;

public record StudyGroupResponse(
    Long studyId,
    String studyName,
    String organization,
    Long peopleCount
) {

}
