package cholog.supp.api.post.dto.request;

public record CreatePostRequest(
    String title,
    String description,
    Long studyId) {

}
