package cholog.supp.common.jwt;

import cholog.supp.db.member.MemberType;
import cholog.supp.db.study.StudyGroup;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.token.access-expiration-time}")
    private long expirationTime;

    public String createToken(StudyGroup studyGroup, MemberType memberType) {
        return Jwts.builder()
            .subject(studyGroup.getId().toString())
            .claim("organization", studyGroup.getOrganization())
            .claim("memberType", memberType)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .expiration(new Date(System.currentTimeMillis() + expirationTime))
            .compact();
    }

    public VerifyToken verifyToken(String token) {
        Claims payload = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .build().parseSignedClaims(token).getPayload();
        Long studyGroupId = Long.valueOf(payload.getSubject());
        MemberType memberType = payload.get("memberType", MemberType.class);
        String organization = payload.get("organization", String.class);
        return new VerifyToken(studyGroupId, memberType, organization);
    }
}
