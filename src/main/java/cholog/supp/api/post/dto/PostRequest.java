package cholog.supp.api.post.dto;

public record PostRequest(
    String title,
    String content,
    Long studyId) {

}
