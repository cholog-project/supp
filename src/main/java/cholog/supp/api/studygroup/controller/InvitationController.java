package cholog.supp.api.studygroup.controller;

import cholog.supp.api.studygroup.dto.request.InvitationRequest;
import cholog.supp.api.studygroup.dto.response.InvitationResponse;
import cholog.supp.api.studygroup.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping("/invitation")
    public ResponseEntity<InvitationResponse> getInvitation(
        @ModelAttribute InvitationRequest invitationRequest) {
        InvitationResponse response = invitationService.createInvitationLink(invitationRequest);
        return ResponseEntity.ok().body(response);
    }
}
