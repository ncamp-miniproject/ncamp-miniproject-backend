package com.model2.mvc.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class AuthenticationRequestDto {
    private final boolean authenticated;
    private final String token;
}
