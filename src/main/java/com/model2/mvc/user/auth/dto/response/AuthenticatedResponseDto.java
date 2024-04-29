package com.model2.mvc.user.auth.dto.response;

import com.model2.mvc.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AuthenticatedResponseDto {
    private final String accessToken;
    private final String refreshToken;
    private final String userId;
    private final Role role;
}
