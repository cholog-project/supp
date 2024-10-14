package cholog.supp.api.study.controller;

import cholog.supp.api.study.dto.request.CreateStudyGroupRequest;
import cholog.supp.api.study.dto.response.JoinGroupResponse;
import cholog.supp.api.study.dto.response.StudyGroupResponse;
import cholog.supp.api.study.service.StudyGroupService;
import cholog.supp.common.auth.Auth;
import cholog.supp.db.member.Member;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @PostMapping()
    public ResponseEntity createGroup(
        @RequestBody CreateStudyGroupRequest request, @Auth Member member)
        throws URISyntaxException {
        Long groupId = studyGroupService.createStudyGroup(request, member);
        return ResponseEntity.created(
            new URI("/api/v1/groups/" + groupId)).build();
    }

    @GetMapping()
    public ResponseEntity<List<StudyGroupResponse>> getGroup(
        @Auth Member member
    ) {
        List<StudyGroupResponse> response = studyGroupService.getGroup(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<StudyGroupResponse> getEachGroup(@PathVariable Long groupId) {
        // TODO: 링크 보내기 버튼이 NODE에게만 보여야 하니까 Response에 MemberType 정보를 포함해야 할 듯
        StudyGroupResponse response = studyGroupService.getEachGroup(groupId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/join")
    public ResponseEntity<JoinGroupResponse> joinGroup(@Auth Member member,
        @RequestParam String token) {
        JoinGroupResponse response = studyGroupService.joinGroup(member, token);
        return ResponseEntity.ok().body(response);
    }
}
