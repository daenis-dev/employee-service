package com.companyname.services.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String APP_USER = "app-user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                            .requestMatchers(HttpMethod.GET, "/health").permitAll()
                            .requestMatchers(HttpMethod.POST, "/v1/accounts/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/v1/employees/**").permitAll() // TODO: remove
                            // .requestMatchers(HttpMethod.GET, "/v1/employees/**").hasAnyRole(APP_USER) TODO: Uncomment
                            .requestMatchers(HttpMethod.GET, "/v1/job-titles/**").permitAll() // TODO: remove
                            // .requestMatchers(HttpMethod.GET, "/v1/job-titles/**").hasAnyRole(APP_USER) TODO: Uncomment
                            .requestMatchers(HttpMethod.GET, "/v1/companies/**").permitAll() // TODO: remove
                            // .requestMatchers(HttpMethod.GET, "/v1/companies/**").hasAnyRole(APP_USER) TODO: uncomment
                            .anyRequest().authenticated();
                })
                .csrf(configurer -> {
                    configurer.ignoringRequestMatchers("/v1/accounts/**");
                });
        http.oauth2ResourceServer(resourceServer -> {
            resourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter));
        });
        http.sessionManagement(sessionManagement -> {
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}