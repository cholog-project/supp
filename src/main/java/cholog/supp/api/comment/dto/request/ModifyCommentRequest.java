package cholog.supp.api.comment.dto.request;

public record ModifyCommentRequest(
    Long commentId,
    String content) {

}
