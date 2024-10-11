package cholog.supp.api.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class InvitationController {

    @GetMapping("/invitation")
    public ResponseEntity invitation() {
        // TODO : 로직 완성
        return null;
    }
}
