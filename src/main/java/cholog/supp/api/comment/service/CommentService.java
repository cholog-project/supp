package cholog.supp.api.comment.service;

import cholog.supp.api.comment.dto.request.CreateCommentRequest;
import cholog.supp.db.comment.Comment;
import cholog.supp.db.comment.CommentRepository;
import cholog.supp.db.member.Member;
import cholog.supp.db.post.Post;
import cholog.supp.db.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void createComment(CreateCommentRequest commentRequest, Member member) {
        Post post = postRepository.findById(commentRequest.postId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문글 입니다."));
        commentRepository.save(new Comment(member, post, commentRequest.content()));
    }
}
