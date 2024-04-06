package com.model2.mvc.auth;

import static org.assertj.core.api.Assertions.*;

import com.model2.mvc.user.domain.Role;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import javax.naming.AuthenticationException;

class TestJwtUtil {

    @Test
    void createToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken("sample-user", Role.USER);
        System.out.println(token);
    }

    @Test
    void resolveClaims() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken("sample-user", Role.USER);
        Claims claims = jwtUtil.resolveClaims(token);
        String sub = (String)claims.get("sub");
        Role role = Role.of((String)claims.get("role")).get();

        assertThat(sub).isEqualTo("sample-user");
        assertThat(role).isEqualTo(Role.USER);
    }

    @Test
    void validateClaims() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken("sample-user", Role.USER);
        Claims claims = jwtUtil.resolveClaims(token);
        assertThat(jwtUtil.validateClaims(claims)).isTrue();
    }
}