package com.model2.mvc.auth.token;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.auth.repository.MemoryRefreshTokenRepository;
import org.junit.jupiter.api.Test;

class TestJwtSupport {

    @Test
    void createToken() {
        JwtSupport jwtSupport = new JwtSupport(new MemoryRefreshTokenRepository());
        String token = jwtSupport.createToken("sample-user", false);
        System.out.println(token);
    }

    @Test
    void resolveClaims() {
        JwtSupport jwtSupport = new JwtSupport(new MemoryRefreshTokenRepository());
        String token = jwtSupport.createToken("sample-user", false);

        assertThat(jwtSupport.extractSubject(token)).isEqualTo("sample-user");
    }

    @Test
    void validateClaims() {
        JwtSupport jwtSupport = new JwtSupport(new MemoryRefreshTokenRepository());
        String token = jwtSupport.createToken("sample-user", false);
        assertThat(jwtSupport.isTokenValid(token, "sample-user")).isTrue();
    }
}