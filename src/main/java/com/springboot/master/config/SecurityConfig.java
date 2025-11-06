package com.springboot.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security Configuration
 * 
 * Demonstrates Spring Security concepts:
 * - Security configuration with @Configuration
 * - @EnableWebSecurity
 * - Security filter chain
 * - Password encoding
 * - HTTP security configuration
 * - CORS handling
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure HTTP Security
     * Demonstrates: Security filter chain, endpoint permissions, CORS
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for REST APIs (enable for web apps with forms)
            .csrf(csrf -> csrf.disable())            
            // Configure CORS
            .cors(Customizer.withDefaults())
            
            // Configure authorization
            .authorizeHttpRequests(auth -> auth
                // Public endpoints (both with and without API prefix for testing)
                .requestMatchers("/api/v1/users/health", "/users/health", "/h2-console/**", "/swagger-ui/**", "/api-docs/**").permitAll()
                
                // Admin endpoints
                .requestMatchers("/api/v1/actuator/**").hasRole("ADMIN")
                
                // Authenticated endpoints
                .requestMatchers("/api/v1/users/**").authenticated()
                
                // All other requests need authentication
                .anyRequest().authenticated()
            )
              // Configure session management for REST API
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            
            // Basic authentication for simplicity (use JWT in production)
            .httpBasic(Customizer.withDefaults())
              // For H2 console (development only) - disable frame options
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable())
            );

        return http.build();
    }

    /**
     * Password encoder bean
     * Demonstrates: Bean configuration, password encoding best practices
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // Strength of 12 for security
    }
}
