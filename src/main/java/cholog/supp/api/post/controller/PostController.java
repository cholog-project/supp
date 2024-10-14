package cholog.supp.api.post.controller;

import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.dto.request.ModifyPostRequest;
import cholog.supp.api.post.dto.request.PostsRequest;
import cholog.supp.api.post.dto.response.EachPostResponse;
import cholog.supp.api.post.dto.response.PostResponse;
import cholog.supp.api.post.service.PostService;
import cholog.supp.common.auth.Auth;
import cholog.supp.db.member.Member;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping()
    public ResponseEntity createPost(@RequestBody CreatePostRequest createPostRequest,
        @Auth Member member) throws URISyntaxException {
        Long postId = postService.createPost(member, createPostRequest);
        return ResponseEntity.created(new URI("/api/v1/post/" + postId)).build();
    }

    @GetMapping()
    public ResponseEntity<List<PostResponse>> getPostList(
        @RequestBody PostsRequest postsRequest) {
        List<PostResponse> response = postService.getPostList(postsRequest);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping()
    public ResponseEntity modifyPost(@Auth Member member,
        @RequestBody ModifyPostRequest modifyPostRequest) {
        postService.modifyPost(member, modifyPostRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@Auth Member member, @PathVariable Long postId) {
        postService.deletePost(member, postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<EachPostResponse> getEachPost(@Auth Member member,
        @PathVariable Long postId) {
        EachPostResponse response = postService.getEachPost(member, postId);
        return ResponseEntity.ok().body(response);
    }
}
