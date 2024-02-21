package com.model2.mvc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = -2871416444202432425L;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        
        String uri = httpRequest.getRequestURI();
        if (uri.endsWith(".do")
                && !uri.equals("/login.do")
                && !uri.equals("/getProduct.do")
                && !uri.equals("/listProduct.do")) {
            System.out.println(getClass().getName() + ".doFilter(): Check if logged in");
            HttpSession session = httpRequest.getSession();
            if (session.isNew() || session.getAttribute("user") == null) {
                System.out.println("User should log in");
                ((HttpServletResponse)response).sendRedirect("/user/loginView.jsp");
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
}
