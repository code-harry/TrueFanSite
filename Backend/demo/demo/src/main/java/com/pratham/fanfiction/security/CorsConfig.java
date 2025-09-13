package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


// This uses CORS support provided by Spring starter security
// Defines Cross Origin Request Security
@Configuration
public class CorsConfig 
{

    @Bean
    public CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration config = new CorsConfiguration();
        // Use the exact origin your frontend uses (127.0.0.1:5500)
        config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:3000"));//Sets that only these 2 domains can make a call to your backend
        //If the above line is not included then there will simple be no allowed origin.
        
        
        
        
        
        
        
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS","PATCH"));//Allows only these methods to be allowed to call for API
        //If the above line is not added then Spring will allow only a very restricted set of methods usually GET, HEAD and maybe POST
        
        
        
        
        
        config.setAllowedHeaders(List.of("*"));           // allow Authorization, Content-Type, etc.
        //Allows all headers to be sent as part of the request
       // By default, browser allows only a very limited set of simple headers in cross origin requests like
        //- Accept, Accept-Language, Content-Language, Content-type
        
        
        
        
        
        config.setExposedHeaders(List.of("Authorization"));
        //Exposes the authorization header to the frontend
        //Normally, browser does not expose certain headers unless explicitly listed
        // This allows JavaScript on thefrontend to read the Authorization header from the responses.
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        config.setAllowCredentials(false);                 // if you need credentials; otherwise false
        //Configures whether to allow cookies, authorization, or TLS client certificate to be included in cross-site requests
        
        
        
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //This is used to map CORS settings to URL patterns
        source.registerCorsConfiguration("/**", config);
        //Applies this corsconfiguration to all the urls/endpoints in the application.
        
        
        
        return source;
    }
}
