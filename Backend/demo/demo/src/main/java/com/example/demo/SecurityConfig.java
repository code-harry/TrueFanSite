//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.http.SessionCreationPolicy;
//
//@Configuration
//@EnableWebSecurity 
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Disable CSRF for APIs
//            .csrf(csrf -> csrf.disable())
//
//            // Disable form-based login
//            .formLogin(form -> form.disable())
//
//            // Disable HTTP Basic Auth
//            .httpBasic(httpBasic -> httpBasic.disable())
//
//            // Stateless session
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//
//            // Authorization rules
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//            );
//
//        return http.build();
//    }
//}
