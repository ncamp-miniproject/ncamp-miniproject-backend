package com.model2.mvc.auth.service;

import com.model2.mvc.common.util.BeanUtil;
import com.model2.mvc.auth.dto.request.AuthRequestDto;
import com.model2.mvc.auth.dto.response.AuthenticatedResponseDto;
import com.model2.mvc.auth.dto.response.LoginUserResponseDto;
import com.model2.mvc.auth.token.TokenSupport;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.RegisterRequestDto;
import com.model2.mvc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final TokenSupport tokenSupport;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final RegisterAuthenticationService registerAuthService;
    private final PasswordEncoder pwEncoder;

    @Override
    public AuthenticatedResponseDto authenticate(AuthRequestDto requestDto) {
        this.authManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUserId(),
                requestDto.getPassword()));
        var user = this.userRepository.findByUserId(requestDto.getUserId()).orElseThrow(); // TODO: Do specific
        String username = user.getUsername();
        String accessToken = this.tokenSupport.createToken(username, false);
        String refreshToken = this.tokenSupport.createToken(username, true);
        return new AuthenticatedResponseDto(accessToken, refreshToken);
    }

    public AuthenticatedResponseDto registerUser(RegisterRequestDto requestDto) throws IllegalStateException {
        try {
            if (!this.registerAuthService.checkAuthentication(requestDto.getEmail())) {
                throw new IllegalStateException("Mail is unauthenticated");
            }
            this.registerAuthService.removeAuthenticationInfo(requestDto.getEmail());
            User newUser = BeanUtil.doMapping(User.class, requestDto);
            newUser.setPassword(this.pwEncoder.encode(newUser.getPassword()));
            newUser.setRegDate(LocalDate.now());
            boolean success = this.userRepository.insertUser(newUser);
            if (!success) {
                throw new IllegalArgumentException("Failed to create an account");
            }
            String username = newUser.getUsername();
            String accessToken = this.tokenSupport.createToken(username, false);
            String refreshToken = this.tokenSupport.createToken(username, true);
            return new AuthenticatedResponseDto(accessToken, refreshToken);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AuthenticatedResponseDto refreshToken(String refreshToken) {
        if (isInvalidRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Not valid refresh token");
        }
        String subject = this.tokenSupport.extractSubject(refreshToken);
        this.tokenSupport.removeRefreshToken(refreshToken);
        return new AuthenticatedResponseDto(this.tokenSupport.createToken(subject, false), this.tokenSupport.createToken(subject, true));
    }

    private boolean isInvalidRefreshToken(String token) {
        return !this.tokenSupport.isRefreshToken(token) || !this.tokenSupport.isValidRefreshToken(token) || this.tokenSupport.isTokenExpired(token);
    }
}
