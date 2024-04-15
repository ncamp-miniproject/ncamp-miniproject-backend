package com.model2.mvc.config.context;

import com.model2.mvc.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.model2.mvc" },
               excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class) })
@EnableAspectJAutoProxy
public class ContextConfig {

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return (username) -> userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
