package com.model2.mvc.auth.token;

import com.model2.mvc.user.domain.Role;

import javax.servlet.http.HttpServletRequest;

public interface TokenBasedAuth {

    public String createToken(String userId, Role role);

    public TokenContent resolveClaim(HttpServletRequest request);

    public TokenContent resolveClaim(String token);
}
