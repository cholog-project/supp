package cholog.supp.api.post.controller;

import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.dto.response.PostResponse;
import cholog.supp.api.post.service.PostService;
import cholog.supp.common.auth.Auth;
import cholog.supp.db.member.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity createPost(@RequestBody CreatePostRequest createPostRequest,
        @Auth Member member) {
        postService.createPost(member, createPostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPostList(
        @RequestParam Long studyId) {
        List<PostResponse> response = postService.getPostList(studyId);
        return ResponseEntity.ok().body(response);
    }
}
