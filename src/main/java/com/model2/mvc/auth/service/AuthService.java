package com.model2.mvc.auth.service;

import com.model2.mvc.auth.dto.request.AuthRequestDto;
import com.model2.mvc.auth.dto.response.AuthenticatedResponseDto;
import com.model2.mvc.auth.dto.response.LoginUserResponseDto;
import com.model2.mvc.user.dto.request.RegisterRequestDto;

public interface AuthService {

    public AuthenticatedResponseDto registerUser(RegisterRequestDto requestDto) throws IllegalStateException;

    public AuthenticatedResponseDto authenticate(AuthRequestDto requestDto);

    public AuthenticatedResponseDto refreshToken(String refreshToken);
}
