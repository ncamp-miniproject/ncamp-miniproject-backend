package com.model2.mvc.filter.product;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.user.domain.Role;
import com.model2.mvc.user.domain.User;

public class AddProductUnauthorizedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        
        if (isNotLoggedIn(session) || isNotAdmin((User)session.getAttribute("user"))) {
            System.out.println("User is not admin");
            ((HttpServletResponse)response).sendRedirect("/index.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    private boolean isNotLoggedIn(HttpSession session) {
        return session.isNew() || session.getAttribute("user") == null;
    }

    private boolean isNotAdmin(User user) {
        return !user.getRole().equals(Role.ADMIN.role());
    }
}
