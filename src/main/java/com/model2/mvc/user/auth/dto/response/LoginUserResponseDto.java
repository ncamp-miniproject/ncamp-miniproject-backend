package com.model2.mvc.user.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class LoginUserResponseDto {
    private String userId;
    private String role;
    private String newAccessToken;
    private String newRefreshToken;
}
