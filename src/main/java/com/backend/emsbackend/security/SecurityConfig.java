package com.backend.emsbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()     // Permit all GET requests
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()    // Permit all POST requests
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()  // Permit all DELETE requests
                        .anyRequest().authenticated()  // Authenticate all requests
                )
                .httpBasic(withDefaults()); // Use HTTP Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("Aayush")
                .password("password")
                .roles("ADMIN")
                .build();

        UserDetails employee = User.builder()
                .username("Employee")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, employee);

    }

}
