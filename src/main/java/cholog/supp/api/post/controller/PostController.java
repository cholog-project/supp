package cholog.supp.api.post.controller;

import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.service.PostService;
import cholog.supp.common.auth.Auth;
import cholog.supp.db.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody CreatePostRequest createPostRequest,
        @Auth Member member) {
        postService.createPost(member, createPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
