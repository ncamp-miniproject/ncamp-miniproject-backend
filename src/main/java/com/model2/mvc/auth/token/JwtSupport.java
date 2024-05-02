package com.model2.mvc.auth.token;

import com.model2.mvc.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Primary
@RequiredArgsConstructor
public class JwtSupport implements TokenSupport {
    private static final String SECRET_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
    private static final JwtParser jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String createToken(String subjectName, boolean refreshToken) {
        Claims claims = getCommonClaims(subjectName, refreshToken);

        final String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

        if (refreshToken) {
            this.refreshTokenRepository.save(token);
        }

        return token;
    }

    private Claims getCommonClaims(String subjectName, boolean refreshToken) {
        Date now = new Date(System.currentTimeMillis());
        Claims claims = Jwts.claims();
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() +
                (refreshToken
                        ? REFRESH_TOKEN_VALIDITY
                        : ACCESS_TOKEN_VALIDITY)));
        claims.setSubject(subjectName);
        claims.put(REFRESH_KEY, String.valueOf(refreshToken));
        return claims;
    }

    @Override
    public String extractSubject(String token) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean isTokenValid(String token, String subjectName) {
        try {
            Claims claims = jwtParser.parseClaimsJws(token).getBody();

            if (this.isRefreshToken(token) && !this.isValidRefreshToken(token)) {
                return false;
            }

            return this.isTokenForRightUser(claims, subjectName);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenForRightUser(Claims claims, String subjectName) {
        return claims.getSubject().equals(subjectName);
    }

    @Override
    public boolean isValidRefreshToken(String token) {
        return this.refreshTokenRepository.retrieve(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        Date expiration = claims.getExpiration();
        return expiration == null || expiration.before(new Date());
    }

    @Override
    public boolean isRefreshToken(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return Boolean.parseBoolean((String) claims.get(REFRESH_KEY));
    }

    @Override
    public void removeRefreshToken(String token) {
        this.refreshTokenRepository.remove(token);
    }
}
