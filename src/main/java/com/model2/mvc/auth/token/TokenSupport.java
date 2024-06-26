package com.model2.mvc.auth.token;

public interface TokenSupport {

//    public static long ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000;
    public static long ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000;
    public static long REFRESH_TOKEN_VALIDITY = 14 * 24 * 60 * 60 * 1000;
    public static String REFRESH_KEY = "isRefresh";

    public String createToken(String subjectName, boolean refreshToken);

    public String extractSubject(String token);

    public boolean isTokenValid(String token, String subjectName);

    public boolean isTokenExpired(String token);

    public boolean isRefreshToken(String token);

    public boolean isValidRefreshToken(String token);

    public void removeRefreshToken(String token);
}
