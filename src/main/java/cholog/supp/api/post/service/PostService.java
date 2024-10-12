package cholog.supp.api.post.service;

import cholog.supp.api.post.dto.request.CreatePostRequest;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberStudyMap;
import cholog.supp.db.member.MemberStudyMapRepository;
import cholog.supp.db.post.Post;
import cholog.supp.db.post.PostRepository;
import cholog.supp.db.study.StudyGroup;
import cholog.supp.db.study.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberStudyMapRepository memberStudyMapRepository;
    private final StudyGroupRepository studyGroupRepository;

    public void createPost(Member member, CreatePostRequest createPostRequest) {
        MemberStudyMap memberStudy = memberStudyMapRepository.findByStudyGroupIdAndMemberId(
                createPostRequest.studyId(), member.getId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 스터디 입니다."));
        StudyGroup studyGroup = memberStudy.getStudyGroup();
        postRepository.save(
            new Post(member, studyGroup, createPostRequest.title(),
                createPostRequest.description()));
    }
}
