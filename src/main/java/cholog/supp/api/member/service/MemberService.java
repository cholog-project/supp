package cholog.supp.api.member.service;

import cholog.supp.api.member.dto.request.SignInMember;
import cholog.supp.api.member.dto.request.SignUpMember;
import cholog.supp.api.member.dto.request.ValidationRequest;
import cholog.supp.api.member.dto.response.ValidationResponse;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String SESSION_KEY = "member";

    public void signIn(SignInMember signinMember, HttpSession session) {
        Member member = memberRepository.findByEmail(signinMember.email())
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 아이디 입니다."));
        boolean matches = passwordEncoder.matches(signinMember.password(), member.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("올바르지 않은 아이디 입니다.");
        }
        session.setAttribute(SESSION_KEY, member);
    }

    public void signUp(SignUpMember signupMember) {
        String encodePassword = passwordEncoder.encode(signupMember.password());
        Member member = new Member(signupMember.email(), encodePassword);
        memberRepository.save(member);
    }

    public ValidationResponse emailValidation(ValidationRequest validationRequest) {
        return new ValidationResponse(
            memberRepository.findByEmail(validationRequest.email()).isPresent());
    }
}
