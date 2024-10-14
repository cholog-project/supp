package cholog.supp.api.comment.controller;

import cholog.supp.api.comment.dto.request.CreateCommentRequest;
import cholog.supp.api.comment.dto.request.ModifyCommentRequest;
import cholog.supp.api.comment.service.CommentService;
import cholog.supp.common.auth.Auth;
import cholog.supp.db.member.Member;
import jakarta.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(
        @Valid @RequestBody CreateCommentRequest commentRequest, @Auth Member member)
        throws URISyntaxException {
        commentService.createComment(commentRequest, member);
        return ResponseEntity.created(new URI("/api/v1/posts/" + commentRequest.postId())).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyComment(@RequestBody ModifyCommentRequest commentRequest,
        @Auth Member member) {
        commentService.modifyComment(commentRequest, member);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@Auth Member member, @PathVariable Long commentId) {
        commentService.deleteComment(member, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
