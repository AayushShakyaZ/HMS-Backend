//package com.backend.emsbackend.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class CorsConfig {
//
//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.addAllowedOrigin("http://localhost:4200");  // Frontend origin
//            configuration.addAllowedMethod("*");  // Allow all HTTP methods
//            configuration.addAllowedHeader("*");  // Allow all headers
//            configuration.setAllowCredentials(true);
//
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
//}
