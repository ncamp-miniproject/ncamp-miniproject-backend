package com.model2.mvc.filter.common;

import com.model2.mvc.user.domain.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class RequestFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        // TODO: For test
        HttpSession session = ((HttpServletRequest)request).getSession();

        User sampleUser = new User().builder()
                .userId("user08")
                .userName("SCOTT")
                .password("1234")
                .role("user")
                .regDate(new Date(System.currentTimeMillis()))
                .build();
        session.setAttribute("user", sampleUser);

        request.setCharacterEncoding("euc-kr");
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}