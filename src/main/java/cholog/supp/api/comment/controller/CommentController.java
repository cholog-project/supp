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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity createComment(@Valid @RequestBody CreateCommentRequest commentRequest,
        @Auth Member member) throws URISyntaxException {
        commentService.createComment(commentRequest, member);
        return ResponseEntity.created(new URI("/api/v1/post/" + commentRequest.postId())).build();
    }

    @PutMapping
    public ResponseEntity modifyComment(@RequestBody ModifyCommentRequest commentRequest,
        @Auth Member member) {
        commentService.modifyComment(commentRequest, member);
        return ResponseEntity.ok().build();
    }
}
