package com.model2.mvc.user.auth.token;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@Primary
public class SimpleJsonTokenSupport implements TokenSupport {
    private static final long ACCESS_TOKEN_VALIDITY = 30;
    private static final long REFRESH_TOKEN_VALIDITY = 14 * 24 * 60;
    private static final String ROLE_KEY = "role";

    @Override
    public String createToken(User user, boolean refreshToken) {
        LocalDateTime now = LocalDateTime.now();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("subject", user.getUsername());
        jsonObj.put("issuedAt", now.toString());
        jsonObj.put("expiration",
                    now.plus(refreshToken ? REFRESH_TOKEN_VALIDITY : ACCESS_TOKEN_VALIDITY, ChronoUnit.MINUTES)
                            .toString());
        jsonObj.put(ROLE_KEY, user.getRole().name());
        return jsonObj.toJSONString();
    }

    @Override
    public String extractUsername(String token) {
        return extractValue(token, "subject");
    }

    @Override
    public Role extractRole(String token) {
        return Role.of(extractValue(token, ROLE_KEY)).orElse(null);
    }

    private String extractValue(String token, String key) {
        JSONObject jsonObject = (JSONObject)JSONValue.parse(token);
        return jsonObject == null ? null : jsonObject.get(key).toString();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return !isTokenExpired(token) && isTokenForRightUser(token, userDetails);
    }

    private boolean isTokenExpired(String token) {
        LocalDateTime expiration = LocalDateTime.parse(extractValue(token, "expiration"));
        return expiration.isBefore(LocalDateTime.now());
    }

    private boolean isTokenForRightUser(String token, UserDetails userDetails) {
        String subject = extractUsername(token);
        return subject.equals(userDetails.getUsername());
    }
}
