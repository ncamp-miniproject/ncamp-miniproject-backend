package com.model2.mvc.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model2.mvc.cart.interceptor.CookieSetter;
import com.model2.mvc.common.aspect.ControllerDebugLoggingAspect;
import com.model2.mvc.common.filter.LoggingFilter;
import com.model2.mvc.common.interceptor.TokenRefreshInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.model2.mvc" },
               includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) },
               useDefaultFilters = false)
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public FilterRegistrationBean<LoggingFilter> filterRegistration() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setName("loggingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO: Should set CORS configuration sophisticated
        registry.addMapping("/**")
                .allowedOrigins("https://www.novemberalpha.shop")
                .allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT")
                .allowCredentials(true);
    }

    private final TokenRefreshInterceptor tokenRefreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieSetter()).addPathPatterns("/cart/items/new");
//        registry.addInterceptor(this.tokenRefreshInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(WebSecurityConfig.WHITE_LIST); // TODO: for development
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/view/");
        vr.setSuffix(".jsp");
        return vr;
    }

    @Bean
    public ControllerDebugLoggingAspect controllerLoggingAspect() {
        return new ControllerDebugLoggingAspect();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
}
