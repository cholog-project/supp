package cholog.supp.common.jwt;

import cholog.supp.db.member.MemberCategory;
import cholog.supp.db.study.StudyGroup;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(StudyGroup studyGroup, MemberCategory memberCategory) {
        return Jwts.builder()
            .subject(studyGroup.getId().toString())
            .claim("memberCategory", memberCategory.getMemberType())
            .compact();
    }
}
