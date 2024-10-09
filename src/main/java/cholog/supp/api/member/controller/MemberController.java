package cholog.supp.api.member.controller;

import cholog.supp.api.member.service.MemberService;
import cholog.supp.api.member.dto.request.SignInMember;
import cholog.supp.api.member.dto.request.SignUpMember;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    public void signUp(@RequestBody SignUpMember signupMember) {
        memberService.signUp(signupMember);
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInMember signinMember,
        HttpServletRequest request) {
        HttpSession session = request.getSession();
        memberService.signIn(signinMember, session);
        return ResponseEntity.ok().build();
    }
}
