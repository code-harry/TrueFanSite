package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;



// A bean which is used to make JWT tokens
@Configuration
@Scope("singleton") // explicit
public class FilterConfig 
{

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() 
    {
        return new JwtAuthenticationFilter();
    }
}
