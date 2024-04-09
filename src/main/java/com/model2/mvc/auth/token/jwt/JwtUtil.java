package com.model2.mvc.auth.token.jwt;

import com.model2.mvc.auth.token.TokenBasedAuth;
import com.model2.mvc.auth.token.TokenContent;
import com.model2.mvc.user.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil implements TokenBasedAuth {
    private static final String SECRET_KEY = "mysecretkey";
    private static final long ACCESS_TOKEN_VALIDITY = 60 * 60 * 1000;
    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String ROLE_KEY = "role";

    private final JwtParser jwtParser;

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
    }

    @Override
    public String createToken(String userId, Role role) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role", role);
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(ACCESS_TOKEN_VALIDITY));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public TokenContent resolveClaim(HttpServletRequest request) {
        try {
            String token = resolveToken(request);
            if (token != null) {
                return parseJstClaims(token);
            }
            return null;
        } catch (ExpiredJwtException e) {
            request.setAttribute("expired", e.getMessage());
            throw e;
        } catch (Exception e) {
            request.setAttribute("invalid", e.getMessage());
            throw e;
        }
    }

    @Override
    public TokenContent resolveClaim(String token) {
        return token != null ? parseJstClaims(token) : null;
    }

    private TokenContent parseJstClaims(String token) {
        Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
        Role role = Role.of((String)claims.get(ROLE_KEY)).orElseThrow();
        return new TokenContent(claims.getSubject(), role, claims.getExpiration());
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER);
        return resolveToken(bearerToken);
    }

    private String resolveToken(String bearerToken) {
        return bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)
               ? bearerToken.substring(TOKEN_PREFIX.length())
               : null;
    }
}
