package cholog.supp.api.study.controller;

import cholog.supp.api.common.APISuccessResponse;
import cholog.supp.api.study.dto.CreateStudyGroupRequest;
import cholog.supp.api.study.service.StudyGroupService;
import cholog.supp.db.member.MemberCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @PostMapping("/group")
    public ResponseEntity<APISuccessResponse<Void>> createGroup(
        @RequestBody CreateStudyGroupRequest request) {
        studyGroupService
    }
}
