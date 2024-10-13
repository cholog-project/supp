package cholog.supp.api.comment.service;

import cholog.supp.api.comment.dto.request.CreateCommentRequest;
import cholog.supp.api.comment.dto.request.ModifyCommentRequest;
import cholog.supp.common.validation.Validation;
import cholog.supp.db.comment.Comment;
import cholog.supp.db.comment.CommentRepository;
import cholog.supp.db.member.Member;
import cholog.supp.db.post.Post;
import cholog.supp.db.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void createComment(CreateCommentRequest commentRequest, Member member) {
        Post post = postRepository.findById(commentRequest.postId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문글 입니다."));
        commentRepository.save(new Comment(member, post, commentRequest.content()));
    }

    public void modifyComment(ModifyCommentRequest commentRequest, Member member) {
        Comment comment = verifyCommentOwner(member, commentRequest.commentId());
        comment.modifyContent(commentRequest.content());
    }

    public void deleteComment(Member member, Long commentId) {
        verifyCommentOwner(member, commentId);
        commentRepository.deleteById(commentId);
    }

    private Comment verifyCommentOwner(Member member, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 의견글 입니다."));
        if (!Validation.verifyMember(member, comment.getMember().getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return comment;
    }
}
