package com.model2.mvc.auth.token;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.user.auth.token.JwtUtil;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.junit.jupiter.api.Test;

class TestJwtUtil {

    @Test
    void createToken() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken(generateUser(), false);
        System.out.println(token);
    }

    @Test
    void resolveClaims() {
        JwtUtil jwtUtil = new JwtUtil();
        String token = jwtUtil.createToken(generateUser(), false);

        assertThat(jwtUtil.extractUsername(token)).isEqualTo("sample-user");
        assertThat(jwtUtil.extractRole(token)).isEqualTo(Role.USER);
    }

    @Test
    void validateClaims() {
        JwtUtil jwtUtil = new JwtUtil();
        User sampleUser = generateUser();
        String token = jwtUtil.createToken(sampleUser, false);
        assertThat(jwtUtil.isTokenValid(token, sampleUser)).isTrue();
    }

    private User generateUser() {
        User sampleUser = new User();
        sampleUser.setUserId("sample-user");
        sampleUser.setRole(Role.USER);
        return sampleUser;
    }
}