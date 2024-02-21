package com.model2.mvc.filter;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model2.mvc.service.user.domain.User;

public class RequestFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO: For test
//        HttpSession session = ((HttpServletRequest)request).getSession();
//        
//        User sampleUser = new User();
//        sampleUser.setUserId("user08");
//        sampleUser.setUserName("SCOTT");
//        sampleUser.setPassword("1234");
//        sampleUser.setRole("admin");
//        sampleUser.setRegDate(new Date(System.currentTimeMillis()));
//        session.setAttribute("user", sampleUser);
        
        request.setCharacterEncoding("euc-kr");
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}