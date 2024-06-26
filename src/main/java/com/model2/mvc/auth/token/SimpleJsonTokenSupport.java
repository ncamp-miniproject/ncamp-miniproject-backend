package com.model2.mvc.auth.token;

import com.model2.mvc.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
@Deprecated
public class SimpleJsonTokenSupport implements TokenSupport {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public String createToken(String subjectName, boolean refreshToken) {
        LocalDateTime now = LocalDateTime.now();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("subject", subjectName);
        jsonObj.put("issuedAt", now.toString());
        jsonObj.put("expiration",
                now.plus(refreshToken ? REFRESH_TOKEN_VALIDITY : ACCESS_TOKEN_VALIDITY, ChronoUnit.MILLIS)
                        .toString());
        jsonObj.put(REFRESH_KEY, refreshToken);

        String token = jsonObj.toJSONString();

        if (refreshToken) {
            this.refreshTokenRepository.save(token);
        }

        return token;
    }

    @Override
    public String extractSubject(String token) {
        return extractValue(token, "subject");
    }

    private String extractValue(String token, String key) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(token);
        return jsonObject == null ? null : jsonObject.get(key).toString();
    }

    @Override
    public boolean isTokenValid(String token, String subjectName) {
        if (subjectName == null || token == null) {
            return false;
        }

        if (this.isRefreshToken(token)) {
            return false;
        }

        boolean tokenNotExpired = !isTokenExpired(token);
        boolean tokenForRightUser = isTokenForRightUser(token, subjectName);

        System.out.println(tokenNotExpired);
        System.out.println(tokenForRightUser);

        return tokenNotExpired && tokenForRightUser;
    }

    @Override
    public boolean isTokenExpired(String token) {
        String value = extractValue(token, "expiration");
        if (value == null) {
            System.out.println("Expiration is null");
            return true;
        }
        LocalDateTime expiration = LocalDateTime.parse(value);
        System.out.println(expiration);
        System.out.println(LocalDateTime.now());
        return expiration.isBefore(LocalDateTime.now());
    }

    private boolean isTokenForRightUser(String token, String subjectName) {
        String subject = extractSubject(token);
        return subject.equals(subjectName);
    }

    @Override
    public boolean isValidRefreshToken(String token) {
        return this.refreshTokenRepository.retrieve(token);
    }

    @Override
    public boolean isRefreshToken(String token) {
        return Boolean.parseBoolean(extractValue(token, REFRESH_KEY));
    }

    @Override
    public void removeRefreshToken(String token) {
        this.refreshTokenRepository.remove(token);
    }
}
