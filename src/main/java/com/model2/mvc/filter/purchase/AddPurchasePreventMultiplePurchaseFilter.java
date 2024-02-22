package com.model2.mvc.filter.purchase;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.CommonConstants;

public class AddPurchasePreventMultiplePurchaseFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = -3397727377956275057L;

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        
        if (!session.isNew() && session.getAttribute("lastPurchaseTime") != null) {
            long lastPurchaseTime = (long)session.getAttribute("lastPurchaseTime");
            if (System.currentTimeMillis() - lastPurchaseTime < CommonConstants.PURCHASE_TIME_GAP_IN_MILLIS) {
                // TODO: Do more thing in case of multiple purchase
                System.out.println("Multiple purchase occurred");
                ((HttpServletResponse)response).sendRedirect("/index.jsp");
                return;
            }
        }
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		session.setAttribute("lastPurchaseTime", System.currentTimeMillis());
	}
}
