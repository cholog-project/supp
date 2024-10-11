package cholog.supp.common.jwt;

import cholog.supp.db.member.MemberType;
import cholog.supp.db.study.StudyGroup;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String createToken(StudyGroup studyGroup, MemberType memberType) {
        return Jwts.builder()
            .subject(studyGroup.getId().toString())
            .claim("organization", studyGroup.getOrganization())
            .claim("memberType", memberType)
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .compact();
    }
}
