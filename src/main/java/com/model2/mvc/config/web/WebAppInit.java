package com.model2.mvc.config.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        rootContext.setConfigLocations("com.model2.mvc.config.context.MyBatisConfig",
                                       "com.model2.mvc.config.context.PropertyConfig",
                                       "com.model2.mvc.config.context.TransactionConfig",
                                       "com.model2.mvc.config.context.ContextConfig");

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);

        servletContext.addListener(contextLoaderListener);

        servletContext.addFilter("encodingFilter", "org.springframework.web.filter.CharacterEncodingFilter")
                .addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter("loginFilter", "com.model2.mvc.filter.common.LoginFilter")
                .addMappingForUrlPatterns(null, false, "/*");

        AnnotationConfigWebApplicationContext servletSpringContext = new AnnotationConfigWebApplicationContext();
        servletSpringContext.setParent(rootContext);
        servletSpringContext.setConfigLocation("com.model2.mvc.config.web.WebConfig");

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("dispatcherServlet",
                                                                                  new DispatcherServlet(servletSpringContext));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
