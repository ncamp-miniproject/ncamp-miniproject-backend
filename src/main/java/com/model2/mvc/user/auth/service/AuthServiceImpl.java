package com.model2.mvc.user.auth.service;

import com.model2.mvc.common.util.BeanUtil;
import com.model2.mvc.user.auth.dto.request.AuthRequestDto;
import com.model2.mvc.user.auth.dto.response.AuthenticatedResponseDto;
import com.model2.mvc.user.auth.token.TokenSupport;
import com.model2.mvc.user.domain.User;
import com.model2.mvc.user.dto.request.RegisterRequestDto;
import com.model2.mvc.user.repository.UserRepository;
import com.model2.mvc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
        String accessToken = this.tokenSupport.createToken(user, false);
        String refreshToken = this.tokenSupport.createToken(user, true);
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
            String accessToken = "";
            String refreshToken = "";
            if (success) {
                accessToken = this.tokenSupport.createToken(newUser, false);
                refreshToken = this.tokenSupport.createToken(newUser, true);
            }
            return new AuthenticatedResponseDto(accessToken, refreshToken);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
