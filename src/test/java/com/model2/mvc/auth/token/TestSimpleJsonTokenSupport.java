package com.model2.mvc.auth.token;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.user.auth.token.SimpleJsonTokenSupport;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.junit.jupiter.api.Test;

class TestSimpleJsonTokenSupport {

    @Test
    void createToken() {
        SimpleJsonTokenSupport simpleJsonTokenSupport = new SimpleJsonTokenSupport();
        String token = simpleJsonTokenSupport.createToken(generateUser(), false);
        System.out.println(token);
    }

    @Test
    void resolveClaims() {
        SimpleJsonTokenSupport simpleJsonTokenSupport = new SimpleJsonTokenSupport();
        String token = simpleJsonTokenSupport.createToken(generateUser(), false);

        assertThat(simpleJsonTokenSupport.extractUsername(token)).isEqualTo("sample-user");
        assertThat(simpleJsonTokenSupport.extractRole(token)).isEqualTo(Role.USER);
    }

    @Test
    void validateClaims() {
        SimpleJsonTokenSupport simpleJsonTokenSupport = new SimpleJsonTokenSupport();
        User sampleUser = generateUser();
        String token = simpleJsonTokenSupport.createToken(sampleUser, false);
        assertThat(simpleJsonTokenSupport.isTokenValid(token, sampleUser)).isTrue();
    }

    private User generateUser() {
        User sampleUser = new User();
        sampleUser.setUserId("sample-user");
        sampleUser.setRole(Role.USER);
        return sampleUser;
    }
}