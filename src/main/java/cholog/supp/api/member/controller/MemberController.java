package cholog.supp.api.member.controller;

import cholog.supp.api.member.service.MemberService;
import cholog.supp.api.member.dto.request.SigninMember;
import cholog.supp.api.member.dto.request.SignupMember;
import jakarta.servlet.http.Cookie;
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
    public ResponseEntity signin(@RequestBody SigninMember signinMember,
        HttpServletResponse response) {
        String token = memberService.signin(signinMember);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
