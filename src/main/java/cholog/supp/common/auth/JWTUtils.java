package cholog.supp.common.auth;

import cholog.supp.db.member.Member;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(Member member) {
        return Jwts.builder()
            .setSubject(member.getId().toString())
            .claim("email",member.getEmail())
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
            .compact();
    }
}
