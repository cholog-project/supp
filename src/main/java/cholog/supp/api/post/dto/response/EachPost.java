package cholog.supp.api.post.dto.response;

import cholog.supp.db.post.Post;

public record EachPost(
    Long postId,
    String postTitle,
    String postDescription,
    Long postMemberId,
    boolean isAuthor
) {

    public EachPost(Post post, boolean isAuthor) {
        this(
            post.getId(),
            post.getTitle(),
            post.getDescription(),
            post.getMember().getId(),
            isAuthor
        );
    }
}
