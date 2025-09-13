package com.pratham.fanfiction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
public class PAsswordEncoder 
{
	
	 // This is used to encode the passwords
	 @Bean
	 public PasswordEncoder passwordEncoder() 
	 {
	     return new BCryptPasswordEncoder();
	 }
	

}
