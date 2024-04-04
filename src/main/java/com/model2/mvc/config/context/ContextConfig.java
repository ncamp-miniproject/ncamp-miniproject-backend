package com.model2.mvc.config.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.model2.mvc" },
               excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
@EnableAspectJAutoProxy
public class ContextConfig {
}
