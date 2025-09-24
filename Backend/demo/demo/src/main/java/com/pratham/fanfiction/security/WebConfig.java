package com.pratham.fanfiction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pratham.fanfiction.rateLimiting.RateLimitInterceptor;



// This uses CORS support provided by Spring MVC
@Configuration
public class WebConfig implements WebMvcConfigurer 
{
	
	
	 @Autowired
	    private RateLimitInterceptor rateLimitInterceptor;

	
    @Override
    public void addCorsMappings(CorsRegistry registry) 
    {
    	registry.addMapping("/**")// addMapping means the url on which the CORS setting applies.
        .allowedOrigins("*")  // add your frontend origin here
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // include OPTIONS
        .allowedHeaders("*")  // allow all headers including Authorization
        .allowCredentials(false);// keeping it true means that credentials and cookies can be sent in the same request.
    	// keeping it false means that credentials and cookies can not be sent in the same request
    }
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(rateLimitInterceptor);
    }
    
    
    
}






