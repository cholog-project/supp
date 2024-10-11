package cholog.supp.common.jwt;

import cholog.supp.db.member.MemberType;
import cholog.supp.db.study.StudyGroup;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(StudyGroup studyGroup, MemberType memberType) {
        return Jwts.builder()
            .subject(studyGroup.getId().toString())
            .claim("organization", studyGroup.getOrganization())
            .claim("memberType", memberType)
            .compact();
    }
}
