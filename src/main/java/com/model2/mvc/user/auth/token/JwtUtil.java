package com.model2.mvc.auth.token;

import com.model2.mvc.auth.token.TokenSupport;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil implements TokenSupport {
    private static final String SECRET_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
    private static final long ACCESS_TOKEN_VALIDITY = 60 * 60 * 1000;
    private static final String ROLE_KEY = "role";

    private final JwtParser jwtParser;

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }

    @Override
    public String createToken(User user) {
        Map<String, Object> extraClaims = Map.of(ROLE_KEY, user.getRole().getRole());

        Date now = new Date(System.currentTimeMillis());

        Claims claims = Jwts.claims();
        claims.setSubject(user.getUsername());
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + TimeUnit.MINUTES.toMillis(ACCESS_TOKEN_VALIDITY)));
        claims.putAll(extraClaims);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public Role extractRole(String token) {
        Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
        return Role.of((String)claims.get(ROLE_KEY)).orElseThrow();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
        return this.isTokenForRightUser(claims, userDetails) && !this.isTokenExpired(claims);
    }

    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    private boolean isTokenForRightUser(Claims claims, UserDetails userDetails) {
        return claims.getSubject().equals(userDetails.getUsername());
    }
}
