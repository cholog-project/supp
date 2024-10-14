package cholog.supp.api.post.dto.request;

public record ModifyPostRequest(
    String title,
    String description,
    Long postId
) {

}
