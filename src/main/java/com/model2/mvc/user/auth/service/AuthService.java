package com.model2.mvc.user.auth.service;

import com.model2.mvc.user.auth.dto.request.AuthRequestDto;
import com.model2.mvc.user.auth.dto.response.AuthenticatedResponseDto;
import com.model2.mvc.user.dto.request.RegisterRequestDto;

public interface AuthService {

    public AuthenticatedResponseDto registerUser(RegisterRequestDto requestDto) throws IllegalStateException;

    public AuthenticatedResponseDto authenticate(AuthRequestDto requestDto);
}
