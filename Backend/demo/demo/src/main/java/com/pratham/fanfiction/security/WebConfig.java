package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



// This uses CORS support provided by Spring MVC
@Configuration
public class WebConfig implements WebMvcConfigurer {
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
}






