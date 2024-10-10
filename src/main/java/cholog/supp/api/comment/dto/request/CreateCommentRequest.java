package cholog.supp.api.comment.dto.request;

public record CreateCommentRequest(
    Long memberId,
    Long postId,
    Long studyId,
    String content
) {

}
