package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
@EnableWebSecurity
public class MyUserDetailsService implements UserDetailsService 
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        User user = userRepository.findByUsername(username)
                     .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
    	http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/signup", "/login").permitAll()
            .anyRequest().authenticated()
        )
        // Configuring form-based login
        .formLogin(form -> form
            .loginPage("/login").permitAll()
        )
        // Configuring logout
        .logout(logout -> logout.permitAll());
        
    return http.build();
    }

    
    
    //
//    In summary:
//    	This code configures a simple web application's security to:
//
//    	Disable CSRF protection.
//
//    	Allow unauthenticated access to the /signup and /login pages.
//
//    	Require authentication for all other pages.
//
//    	Use a form-based login process, with the login page located at /login.
//
//    	Allow any user to perform a logout.
    
    
}
