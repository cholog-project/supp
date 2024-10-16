package cholog.supp.api.post.dto.response;

import cholog.supp.db.comment.Comment;

public record EachComment(
    Long commentId,
    String commentContent,
    String commentMemberType,
    Long commentMemberId,
    boolean isAuthor
) {

    public EachComment(Comment comment, String commentMemberType, boolean isAuthor) {
        this(
            comment.getId(),
            comment.getContent(),
            commentMemberType,
            comment.getMember().getId(),
            isAuthor
        );
    }
}
