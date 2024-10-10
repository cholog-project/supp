package cholog.supp.api.comment.dto.request;

public record CreateCommentRequest(
    Long postId,
    Long studyId,
    String content
) {

}
