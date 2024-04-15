package com.model2.mvc;

import com.model2.mvc.config.context.ContextConfig;
import com.model2.mvc.config.context.MyBatisConfig;
import com.model2.mvc.config.context.PropertyConfig;
import com.model2.mvc.config.web.WebConfig;
import com.model2.mvc.config.web.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
        exclude = { DataSourceAutoConfiguration.class },
        scanBasePackageClasses = {
                ContextConfig.class,
                MyBatisConfig.class,
                PropertyConfig.class,
                WebConfig.class,
                WebSecurityConfig.class
        }
)
public class ApplicationEntry extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        System.out.println("=================================");
        System.out.println("SpringBootServletInitializer");
        System.out.println("=================================");
        return builder.sources(ApplicationEntry.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationEntry.class, args);
    }
}
