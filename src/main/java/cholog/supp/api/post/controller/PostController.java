package cholog.supp.api.post.controller;

import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.dto.request.ModifyPostRequest;
import cholog.supp.api.post.dto.request.PostsRequest;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return ResponseEntity.status(HttpStatus.CREATED).build(); //TODO : url 추가 필요
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponse>> getPostList(
        @RequestBody PostsRequest postsRequest) {
        List<PostResponse> response = postService.getPostList(postsRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/post")
    public ResponseEntity modifyPost(@Auth Member member,
        @RequestBody ModifyPostRequest modifyPostRequest) {
        postService.modifyPost(member, modifyPostRequest);
        return ResponseEntity.ok().build();
    }
}
