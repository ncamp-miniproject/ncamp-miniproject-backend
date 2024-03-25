package com.model2.mvc.common.interceptor;

import com.model2.mvc.common.util.AuthenticationHelper;
import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationForSellerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        // TODO
//        return AuthenticationHelper.authenticate(request, response, )
        return true;
    }
}
