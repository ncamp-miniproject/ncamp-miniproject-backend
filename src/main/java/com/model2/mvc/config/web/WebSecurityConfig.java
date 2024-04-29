package com.model2.mvc.config.web;

import com.model2.mvc.user.auth.filter.AuthFilter;
import com.model2.mvc.user.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String NEW_ACCESS_TOKEN_HEADER = "New-Access-Token";
    public static final String NEW_REFRESH_TOKEN_HEADER = "New-Refresh-Token";
    public static final Map<String, List<HttpMethod>> WHITE_LIST = Map.of(
            "/api/auth/**", List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH, HttpMethod.PUT),
            "/api/categories", List.of(HttpMethod.GET),
            "/api/products/**", List.of(HttpMethod.GET)
    );

    private static final List<Role> ALL_ROLES = Arrays.asList(Role.values());

    public static final Map<String, Map<HttpMethod, List<Role>>> ALLOWED_REQUESTS = Map.of(
            "/api/users", Map.of(
                    HttpMethod.GET, List.of(Role.ADMIN)
            ),
            "/api/users/*", Map.of(
                    HttpMethod.GET, ALL_ROLES,
                    HttpMethod.DELETE, ALL_ROLES
            ),
            "/api/cart", Map.of(
                    HttpMethod.GET, List.of(Role.USER),
                    HttpMethod.DELETE, List.of(Role.USER),
                    HttpMethod.POST, List.of(Role.USER)
            ),
            "/api/categories/**", Map.of(
                    HttpMethod.POST, List.of(Role.ADMIN),
                    HttpMethod.PATCH, List.of(Role.ADMIN)
            ),
            "/api/products", Map.of(
                    HttpMethod.POST, List.of(Role.SELLER),
                    HttpMethod.PATCH, List.of(Role.SELLER, Role.ADMIN)
            ),
            "/api/purchases/*", Map.of(
                    HttpMethod.POST, List.of(Role.USER),
                    HttpMethod.GET, ALL_ROLES
            ),
            "/api/purchases/sale/list", Map.of(
                    HttpMethod.GET, List.of(Role.ADMIN)
            ),
            "/api/purchases/*/tran-code", Map.of(
                    HttpMethod.GET, ALL_ROLES,
                    HttpMethod.PATCH, List.of(Role.ADMIN)
            ),
            "/api/seller/**", Map.of(
                    HttpMethod.POST, List.of(Role.USER),
                    HttpMethod.PATCH, List.of(Role.ADMIN),
                    HttpMethod.GET, ALL_ROLES,
                    HttpMethod.DELETE, List.of(Role.ADMIN)
            )
    );

    private final AuthFilter authFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        // TODO: replace with BCryptPasswordEncoder after testing
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return String.valueOf(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return String.valueOf(rawPassword).equals(encodedPassword);
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder,
                                                         UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http,
                                         AuthenticationProvider authProvider,
                                         AuthenticationManager authManager) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) -> {
                    WHITE_LIST.forEach((url, met) -> {
                        met.forEach((m) -> req.requestMatchers(new AntPathRequestMatcher(url, m.name())).permitAll());
                    });
                    ALLOWED_REQUESTS.forEach((k, v) -> {
                        v.forEach((m, r) -> {
                            req.requestMatchers(new AntPathRequestMatcher(k, m.name())).hasAnyAuthority(r.stream().map(Role::name).toArray(String[]::new));
                        });
                    });
//                    req.anyRequest().permitAll(); // TODO: just for development
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authManager)
                .authenticationProvider(authProvider)
                .addFilterBefore(this.authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
