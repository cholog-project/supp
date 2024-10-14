package cholog.supp.api.post.service;

import cholog.supp.api.post.dto.ModifyPost;
import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.api.post.dto.request.ModifyPostRequest;
import cholog.supp.api.post.dto.request.PostsRequest;
import cholog.supp.api.post.dto.response.EachComment;
import cholog.supp.api.post.dto.response.EachPost;
import cholog.supp.api.post.dto.response.EachPostResponse;
import cholog.supp.api.post.dto.response.PostResponse;
import cholog.supp.common.validation.Validation;
import cholog.supp.db.comment.Comment;
import cholog.supp.db.comment.CommentRepository;
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
    private final CommentRepository commentRepository;

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
        Validation.verifyEmptyList(allPost);
        return allPost.stream()
            .map(it -> new PostResponse(it.getId(), it.getTitle(), it.getCreatedDate())).toList();
    }

    public void modifyPost(Member member, ModifyPostRequest modifyPostRequest) {
        Post post = verifyPostOwner(member, modifyPostRequest.postId());
        post.modifyPost(new ModifyPost(modifyPostRequest.title(), modifyPostRequest.description()));
    }

    public void deletePost(Member member, Long postId) {
        verifyPostOwner(member, postId);
        commentRepository.deleteAllByPostId(postId);
        postRepository.deleteById(postId);
    }

    private Post verifyPostOwner(Member member, Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문글 입니다."));
        if (!Validation.verifyMember(member, post.getMember().getId())) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
        return post;
    }

    public EachPostResponse getEachPost(Member member, Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 질문글 입니다."));
        boolean isAuthor = Validation.verifyMember(member, post.getMember().getId());
        EachPost eachPost = new EachPost(post, isAuthor);
        List<Comment> allComment = commentRepository.findAllByPostId(postId);
        Validation.verifyEmptyList(allComment);
        List<EachComment> comments = getCommentList(
            post.getStudy().getId(),
            allComment,
            member);
        return new EachPostResponse(eachPost, comments);
    }

    private List<EachComment> getCommentList(Long studyId, List<Comment> comments, Member member) {
        return comments.stream().map(comment -> {
            Member nowCommentMember = comment.getMember();
            MemberStudyMap memberStudyMap = memberStudyMapRepository.findByStudyGroupIdAndMemberId(
                    studyId,
                    nowCommentMember.getId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));
            String memberType = memberStudyMap.getMemberCategory().getMemberType().name();
            boolean isAuthor = Validation.verifyMember(member, nowCommentMember.getId());
            return new EachComment(comment, memberType, isAuthor);
        }).toList();
    }

    public List<PostResponse> getPostList(Long studyId) {
        List<Post> allPost = postRepository.findAllByStudyIdDesc(studyId);
        return allPost.stream()
            .map(it -> new PostResponse(it.getId(), it.getTitle(), it.getCreatedDate())).toList();
    }
}
