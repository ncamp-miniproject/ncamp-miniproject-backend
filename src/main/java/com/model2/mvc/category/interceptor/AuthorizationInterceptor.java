package com.model2.mvc.category.interceptor;

import com.model2.mvc.common.util.AuthorizationHelper;
import com.model2.mvc.user.domain.Role;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return AuthorizationHelper.authorize(request, response, Role.ADMIN, HttpMethod.POST, HttpMethod.PATCH);
    }
}
