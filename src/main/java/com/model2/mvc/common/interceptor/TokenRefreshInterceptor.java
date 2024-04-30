package com.model2.mvc.common.interceptor;

import com.model2.mvc.config.web.WebSecurityConfig;
import com.model2.mvc.auth.token.TokenSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenRefreshInterceptor implements HandlerInterceptor {
    private final TokenSupport tokenSupport;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) {
        String authorization = request.getHeader(WebSecurityConfig.AUTH_HEADER);
        if (authorization == null || !authorization.startsWith(WebSecurityConfig.TOKEN_PREFIX)) {
            return false;
        }

        String token = authorization.substring(WebSecurityConfig.TOKEN_PREFIX.length());
        String username = this.tokenSupport.extractUsername(token);
        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            response.addHeader(WebSecurityConfig.NEW_ACCESS_TOKEN_HEADER, this.tokenSupport.createToken(userDetails.getUsername(), false));
            if (this.tokenSupport.isRefreshToken(token)) {
                response.addHeader(WebSecurityConfig.NEW_REFRESH_TOKEN_HEADER, this.tokenSupport.createToken(userDetails.getUsername(), true));
                this.tokenSupport.removeToken(token);
            }
            return true;
        } catch (UsernameNotFoundException e) {
            log.info("Username not found: " + username);
            return false;
        }
    }
}
