package cholog.supp.api.member;

import cholog.supp.common.LoginMember;
import cholog.supp.common.SignupMember;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupMember signupMember) {
        memberService.signup(signupMember);
    }

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody LoginMember loginMember,
        HttpServletResponse response) {

        /**
         * TODO
         * 완성.
         */
        return null;
    }
}
