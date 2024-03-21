package com.model2.mvc.filter.common;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class LoginFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = -2871416444202432425L;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        login(httpRequest);

        String uri = httpRequest.getRequestURI();
        if (uri.endsWith(".do") &&
            !uri.equals("/login.do") &&
            !uri.equals("/getProduct.do") &&
            !uri.equals("/listProduct.do")) {

            System.out.println(getClass().getName() + ".doFilter(): Check if logged in");
            HttpSession session = httpRequest.getSession();
            if (session.isNew() || session.getAttribute("user") == null) {
                System.out.println("User should log in");
                ((HttpServletResponse)response).sendRedirect("/loginView.do");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private void login(HttpServletRequest request) {
        HttpSession session = request.getSession();

        User sampleUser = new User();
        sampleUser.setUserId("user08");
        sampleUser.setUserName("SCOTT");
        sampleUser.setPassword("1234");
        sampleUser.setRole(Role.USER);
        sampleUser.setRegDate(new Date(System.currentTimeMillis()));
        session.setAttribute("user", sampleUser);
    }
}
