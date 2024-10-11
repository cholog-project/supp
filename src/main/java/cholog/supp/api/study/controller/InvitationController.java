package cholog.supp.api.study.controller;

import cholog.supp.api.study.dto.request.InvitationRequest;
import cholog.supp.api.study.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InvitationController {

    private final InvitationService invitationService;

    @GetMapping("/invitation")
    public ResponseEntity<String> getInvitationLink(
        @RequestBody InvitationRequest invitationRequest) {
        String response = invitationService.createInvitationLink(invitationRequest);
        return ResponseEntity.ok().body(response);
    }
}
