package cholog.supp.api.comment.dto.request;

public record CreateCommentRequest(
    Long postId,
    String content
) {

}
