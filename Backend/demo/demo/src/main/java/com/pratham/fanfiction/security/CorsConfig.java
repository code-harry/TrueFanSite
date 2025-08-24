package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


// This uses CORS support provided by Spring starter security
//
@Configuration
public class CorsConfig 
{

    @Bean
    public CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration config = new CorsConfiguration();
        // Use the exact origin your frontend uses (127.0.0.1:5500)
        config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:3000"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS","PATCH"));
        config.setAllowedHeaders(List.of("*"));           // allow Authorization, Content-Type, etc.
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(false);                 // if you need credentials; otherwise false

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
