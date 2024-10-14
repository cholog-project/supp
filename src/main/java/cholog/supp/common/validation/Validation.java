package cholog.supp.common.validation;

import cholog.supp.db.comment.Comment;
import cholog.supp.db.member.Member;
import cholog.supp.db.post.Post;

public class Validation {

    public static boolean verifyMember(Member member, Long compareMemberId) {
        return member.getId().equals(compareMemberId);
    }

    public static void verifyCommentOwner(Member member, Comment comment) {
        if (!verifyMember(member, comment.getMember().getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }

    public static void verifyPostOwner(Member member, Post post) {
        if (!verifyMember(member, post.getMember().getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }
}
