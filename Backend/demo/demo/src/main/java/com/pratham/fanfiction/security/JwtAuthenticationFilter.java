package com.pratham.fanfiction.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{

    @Autowired JwtUtil jwtUtil;
    @Autowired AppUserRepository repo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException, java.io.IOException 
    {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) 
        {
            String token = header.substring(7);
            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaims(token);
                String username = claims.getSubject();
                int tokenVersionInToken = claims.get("tv", Integer.class);

                AppUser user = repo.findById(username).orElse(null);
                if (user != null && user.getTokenVersion() == tokenVersionInToken) 
                {
                    // build authentication and set context
                    UserDetails ud = User.withUsername(username).password(user.getPasswordHash()).authorities("USER").build();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
