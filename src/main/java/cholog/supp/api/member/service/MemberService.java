package cholog.supp.api.member.service;

import cholog.supp.api.member.dto.request.SigninMember;
import cholog.supp.api.member.dto.request.SignupMember;
import cholog.supp.common.auth.JWTUtils;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    public String signin(SigninMember signinMember) {
        Member member = memberRepository.findByEmail(signinMember.email())
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 아이디 입니다."));
        boolean matches = passwordEncoder.matches(signinMember.password(), member.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("올바르지 않은 아이디 입니다.");
        }

        return jwtUtils.createToken(member);
    }

    public void signup(SignupMember signupMember) {
        String encodePassword = passwordEncoder.encode(signupMember.password());
        Member member = new Member(signupMember.email(), encodePassword);
        if (memberRepository.findByEmail(signupMember.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }
}
