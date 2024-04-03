package com.model2.mvc.config.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("==================================");
        System.out.println("WebAppInit");
        System.out.println("==================================");

        servletContext.setInitParameter("contextClass",
                                        "org.springframework.web.context.support.XmlWebApplicationContext");
        servletContext.setInitParameter("contextConfigLocation", "classpath*:spring-config/context-*.xml");

        servletContext.addListener("org.springframework.web.context.ContextLoaderListener");

        servletContext.addFilter("encodingFilter", "org.springframework.web.filter.CharacterEncodingFilter")
                .addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter("loginFilter", "com.model2.mvc.filter.common.LoginFilter")
                .addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet",
                                                                                  "org.springframework.web.servlet.DispatcherServlet");
        dispatcherServlet.setInitParameter("contextConfigLocation", "/WEB-INF/spring-servlet.xml");
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
