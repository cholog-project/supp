package cholog.supp.api.member.service;

import cholog.supp.api.member.dto.request.SignInMember;
import cholog.supp.api.member.dto.request.SignUpMember;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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
        if (memberRepository.findByEmail(signupMember.email()).isPresent()) {
            throw new DuplicateKeyException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
    }

    public void emailValidation(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }
}
