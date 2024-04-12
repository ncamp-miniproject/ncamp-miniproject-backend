package com.model2.mvc.user.auth.token;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenSupport {

    public String createToken(User user, boolean refreshToken);

    public String extractUsername(String token);

    public Role extractRole(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
