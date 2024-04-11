package com.model2.mvc.auth.service;

import com.model2.mvc.user.dto.request.AuthenticationRequestDto;
import com.model2.mvc.user.dto.response.AuthenticationResponseDto;

public interface AuthService {

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
