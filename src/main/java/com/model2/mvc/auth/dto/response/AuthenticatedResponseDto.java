package com.model2.mvc.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AuthenticatedResponseDto {
    private final String accessToken;
    private final String refreshToken;
}
