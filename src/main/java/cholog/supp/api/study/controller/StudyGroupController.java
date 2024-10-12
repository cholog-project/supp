package cholog.supp.api.study.controller;

import cholog.supp.api.study.dto.request.CreateStudyGroupRequest;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @PostMapping
    public ResponseEntity createGroup(
        @RequestBody CreateStudyGroupRequest request, @Auth Member member)
        throws URISyntaxException {
        Long groupId = studyGroupService.createStudyGroup(request, member);
        return ResponseEntity.created(
            new URI("/api/v1/group/" + groupId)).build();
    }

    @GetMapping
    public ResponseEntity<List<StudyGroupResponse>> getGroup(
        @Auth Member member
    ) {
        var response = studyGroupService.getGroup(member);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<StudyGroupResponse> getEachGroup(@PathVariable Long groupId) {
        StudyGroupResponse response = studyGroupService.getEachGroup(groupId);
        return ResponseEntity.ok().body(response);
    }
}
