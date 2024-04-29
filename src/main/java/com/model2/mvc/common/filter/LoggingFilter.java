package com.model2.mvc.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        log.debug("Request");
        log.debug("Request-------------Header------------------");
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            log.info("{}={}", headerName, httpRequest.getHeader(headerName));
        }
        chain.doFilter(request, response);

        log.debug("Response-------------Header--------------------");
        httpResponse.getHeaderNames().forEach((hn) -> {
            log.debug("{}={}", hn, httpResponse.getHeader(hn));
        });
        log.debug("--------------------------------");
    }
}
