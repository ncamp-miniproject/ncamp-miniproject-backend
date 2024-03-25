package com.model2.mvc.common.interceptor;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationForUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        User user = (User)request.getSession().getAttribute("user");
        if (user.getRole() != Role.USER) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
        return true;
    }
}
