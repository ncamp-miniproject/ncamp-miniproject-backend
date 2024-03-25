package com.model2.mvc.cart.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSetter implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        Cookie cookie = new Cookie("cart", (String)modelAndView.getModel().get("cartCookieValue"));
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
