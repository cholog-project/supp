package cholog.supp;

import cholog.supp.api.member.dto.request.SignupMember;
import cholog.supp.api.member.service.MemberService;
import cholog.supp.common.auth.JWTUtils;
import cholog.supp.db.member.Member;
import cholog.supp.db.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class JWTTest {

    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    void secretKeyTest() {
//        Member member = new Member("1234", "1234");
        Member findMember = memberRepository.findByEmail("1234").get();

        System.out.println("token = " + jwtUtils.createToken(findMember));
    }

    @Test
    void signupTest() {
        memberRepository.findByEmail("4321");
        memberService.signup(new SignupMember("4321", "4321"));
    }

}
