package cholog.supp.api.post.service;

import cholog.supp.api.post.dto.ModifyPost;
import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.dto.request.ModifyPostRequest;
import cholog.supp.api.post.dto.request.PostsRequest;
import cholog.supp.api.post.dto.response.PostResponse;
import cholog.supp.common.validation.Validation;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberStudyMap;
import cholog.supp.db.member.MemberStudyMapRepository;
import cholog.supp.db.post.Post;
import cholog.supp.db.post.PostRepository;
import cholog.supp.db.study.StudyGroup;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberStudyMapRepository memberStudyMapRepository;

    public Long createPost(Member member, CreatePostRequest createPostRequest) {
        MemberStudyMap memberStudy = memberStudyMapRepository.findByStudyGroupIdAndMemberId(
                createPostRequest.studyId(), member.getId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        StudyGroup studyGroup = memberStudy.getStudyGroup();
        Post post = postRepository.save(
            new Post(member, studyGroup, createPostRequest.title(),
                createPostRequest.description()));
        return post.getId();
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostList(PostsRequest postsRequest) {
        List<Post> allPost = postRepository.findAllByStudyIdDesc(postsRequest.studyId());
        return allPost.stream()
            .map(it -> new PostResponse(it.getId(), it.getTitle(), it.getCreatedDate())).toList();
    }

    public void modifyPost(Member member, ModifyPostRequest modifyPostRequest) {
        Post post = verifyPost(member, modifyPostRequest.postId());
        post.modifyPost(new ModifyPost(modifyPostRequest.title(), modifyPostRequest.description()));
    }

    public void deletePost(Member member, Long postId) {
        verifyPost(member, postId);
        postRepository.deleteById(postId);
    }

    private Post verifyPost(Member member, Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문글 입니다."));
        if (!Validation.verifyMember(member, post.getMember().getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return post;
    }
}
