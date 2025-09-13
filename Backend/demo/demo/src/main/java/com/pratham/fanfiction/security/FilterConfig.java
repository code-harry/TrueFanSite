package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



// A bean which is used to make JWT tokens
@Configuration
public class FilterConfig 
{

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() 
    {
        return new JwtAuthenticationFilter();
    }
}
