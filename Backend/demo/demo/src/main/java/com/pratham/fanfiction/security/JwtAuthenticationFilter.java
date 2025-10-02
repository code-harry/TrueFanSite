////package com.pratham.fanfiction.security;
////
////import jakarta.servlet.FilterChain;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.http.HttpServletRequest;
////import jakarta.servlet.http.HttpServletResponse;
////
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Component;
////import org.springframework.web.filter.OncePerRequestFilter;
////
////import com.pratham.fanfiction.sql.AppUserEntity;
////import com.pratham.fanfiction.sql.AppUserEntityRepository;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.io.IOException;
////
////@Component
////public class JwtAuthenticationFilter extends OncePerRequestFilter // which ensures this filter runs this once per request
////{
////
////	
////	
////	// Used to generate and validate a JWT token
////    @Autowired 
////    JwtUtil jwtUtil;
////    
////    
////    
////    
////    // Used to get data from mongodb
////    @Autowired 
////    AppUserRepository repo;
////    
////    @Autowired
////    AppUserEntityRepository repoSQL;
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
////            throws ServletException, IOException, java.io.IOException 
////    {
////
////        String header = request.getHeader("Authorization");
////        if (header != null && header.startsWith("Bearer ")) 
////        {
////            String token = header.substring(7);
////            if (jwtUtil.validateToken(token)) 
////            {
////                Claims claims = jwtUtil.getClaims(token);
////                String username = claims.getSubject();
////                int tokenVersionInToken = claims.get("tv", Integer.class);
////
////                
////                //Old code with MongoDb
//////                AppUser user = repo.findById(username).orElse(null);
////                
////                
////                //New code with SQL
////                  AppUserEntity user =     repoSQL.findByUsername(username);
////                
////                
////                
////                if (user != null && user.getTokenVersion() == tokenVersionInToken) 
////                {
////                    // build authentication and set context
//////                    UserDetails ud = User.withUsername(username).password(user.getPasswordHash()).authorities("USER").build();
////                	// Get the role directly from the user object fetched from the database
//////                	UserDetails ud = User.withUsername(username)
//////                	                     .password(user.getPasswordHash())
//////                	                     .authorities(user.getRole()) // <-- THE FIX
////////                	                     .authorities("ROLE_USER") // if using roles
//////                	                     .build();
////                	
////                	
////                	UserDetails ud = User.withUsername(username)
////                            .password(user.getPasswordHash())
////                            .roles("USER") // <- use roles here
////                            .build();
////
////                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
////                    SecurityContextHolder.getContext().setAuthentication(auth);
////                    
//////                    Authentication authi = SecurityContextHolder.getContext().getAuthentication(); 
//////                    logger.info("Authenticated user: {}", (Throwable) authi);
////                }
////            }
////        }
////        chain.doFilter(request, response);
////    }
////}
//
//
//
//
//package com.pratham.fanfiction.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.pratham.fanfiction.sql.AppUserEntity;
//import com.pratham.fanfiction.sql.AppUserEntityRepository;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.io.IOException;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private AppUserEntityRepository repoSQL;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException, java.io.IOException {
//
//        String header = request.getHeader("Authorization");
//
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//
//            if (jwtUtil.validateToken(token)) {
//                Claims claims = jwtUtil.getClaims(token);
//                String username = claims.getSubject();
//                int tokenVersionInToken = claims.get("tv", Integer.class);
//
//                AppUserEntity user = repoSQL.findByUsername(username);
//
//                if (user != null && user.getTokenVersion() == tokenVersionInToken) {
//                    // Build Spring Security UserDetails with proper ROLE
//                    UserDetails ud = User.withUsername(username)
//                                         .password(user.getPasswordHash())
//                                         .roles("USER") // <-- ensures ROLE_USER internally
//                                         .build();
//
//                    UsernamePasswordAuthenticationToken auth =
//                            new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities());
//
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//
//                    // Logging for debugging
//                    Authentication authCheck = SecurityContextHolder.getContext().getAuthentication();
//                    logger.info("JWT validated. User authenticated: {}", username);
//                    logger.info("Authorities: {}", authCheck.getAuthorities());
//                } else {
//                    logger.warn("Token version mismatch or user not found for username: {}", username);
//                }
//            } else {
//                logger.warn("Invalid JWT token received");
//            }
//        } else {
//            logger.debug("No Authorization header found or does not start with Bearer");
//        }
//
//        chain.doFilter(request, response);
//    }
//}


package com.pratham.fanfiction.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pratham.fanfiction.sql.AppUserEntity;
import com.pratham.fanfiction.sql.AppUserEntityRepository;

import io.jsonwebtoken.Claims;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserEntityRepository repoSQL;

    // It's good practice to use the Slf4j logger from the class itself.
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaims(token);
                String username = claims.getSubject();
                int tokenVersionInToken = claims.get("tv", Integer.class);

                AppUserEntity user = repoSQL.findByUsername(username);

                if (user != null && user.getTokenVersion() == tokenVersionInToken) {
                    
                    // --- THE FIX IS HERE ---
                    // Instead of hardcoding .roles("USER"), we now use the actual role
                    // from the database, which is stored in user.getRole().
                    // This ensures that if you add other roles later (e.g., "ROLE_ADMIN"),
                    // the correct permissions will be applied.
                    UserDetails userDetails = User.withUsername(username)
                            .password(user.getPasswordHash()) // Password is required but not used for auth here
                            .authorities(user.getRole())      // Use the role from the database
                            .build();

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Set the authentication object in the security context.
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    // Logging for debugging
                    logger.info("JWT validated. User authenticated: {}", username);
                    logger.info("Authorities: {}", auth.getAuthorities());

                } else {
                    logger.warn("Token version mismatch or user not found for username: {}", username);
                }
            } else {
                logger.warn("Invalid JWT token received");
            }
        }

        // Continue the filter chain.
        chain.doFilter(request, response);
    }
}

