package com.model2.mvc.user.auth.token;

import com.model2.mvc.user.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@Primary
@RequiredArgsConstructor
public class SimpleJsonTokenSupport implements TokenSupport {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String createToken(String subjectName, boolean refreshToken) {
        LocalDateTime now = LocalDateTime.now();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("subject", subjectName);
        jsonObj.put("issuedAt", now.toString());
        jsonObj.put("expiration",
                    now.plus(refreshToken ? TokenSupport.REFRESH_TOKEN_VALIDITY : TokenSupport.ACCESS_TOKEN_VALIDITY, ChronoUnit.MILLIS)
                            .toString());
        jsonObj.put(TokenSupport.REFRESH_KEY, refreshToken);

        String token = jsonObj.toJSONString();

        if (refreshToken) {
            this.refreshTokenRepository.save(token);
        }

        return token;
    }

    @Override
    public String extractUsername(String token) {
        return extractValue(token, "subject");
    }

    private String extractValue(String token, String key) {
        JSONObject jsonObject = (JSONObject)JSONValue.parse(token);
        return jsonObject == null ? null : jsonObject.get(key).toString();
    }

    @Override
    public boolean isTokenValid(String token, String subjectName) {
        if (subjectName == null || token == null) {
            return false;
        }

        if (this.isRefreshToken(token) && !this.isValidRefreshToken(token)) {
            return false;
        }

        return !isTokenExpired(token) && isTokenForRightUser(token, subjectName);
    }

    @Override
    public boolean isTokenExpired(String token) {
        String value = extractValue(token, "expiration");
        if (value == null) {
            return true;
        }
        LocalDateTime expiration = LocalDateTime.parse(value);
        return expiration.isBefore(LocalDateTime.now());
    }

    private boolean isTokenForRightUser(String token, String subjectName) {
        String subject = extractUsername(token);
        return subject.equals(subjectName);
    }

    @Override
    public boolean isValidRefreshToken(String token) {
        return this.refreshTokenRepository.retrieve(token);
    }

    @Override
    public boolean isRefreshToken(String token) {
        return Boolean.parseBoolean(extractValue(token, TokenSupport.REFRESH_KEY));
    }

    @Override
    public void removeToken(String token) {
        this.refreshTokenRepository.remove(token);
    }
}
