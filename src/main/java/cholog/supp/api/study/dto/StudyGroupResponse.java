package cholog.supp.api.study.dto;

public record StudyGroupResponse(
    Long studyId,
    String studyName,
    String organization,
    Long peopleCount
) {

}
