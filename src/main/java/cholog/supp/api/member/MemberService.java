package cholog.supp.api.member;

import cholog.supp.common.LoginMember;
import cholog.supp.common.SignupMember;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public String signin(LoginMember loginMember) {
        Member member = memberRepository.findByEmail(loginMember.email())
            .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 아이디 입니다."));
        boolean matches = passwordEncoder.matches(loginMember.password(), member.getPassword());
        if (!matches) {
            throw new IllegalArgumentException("올바르지 않은 아이디 입니다.");
        }

        /**
         * TODO
         * JWT 토큰 생성해서 반환하기
         */
        return "";
    }

    public void signup(SignupMember signupMember) {
        String encodePassword = passwordEncoder.encode(signupMember.password());
        Member member = new Member(signupMember.email(), encodePassword);
        memberRepository.save(member);
    }
}
