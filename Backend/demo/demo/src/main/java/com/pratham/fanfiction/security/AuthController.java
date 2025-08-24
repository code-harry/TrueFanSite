package com.pratham.fanfiction.security;

import java.security.Principal;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController 
{

    @Autowired 
    AppUserRepository repo;
    
    @Autowired 
    PasswordEncoder passwordEncoder;
    
    @Autowired 
    JwtUtil jwtUtil;

    
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String,String> body) 
    {
    	logger.info("Signup API is called");
        String username = body.get("username");
        String password = body.get("password");
        if (repo.existsById(username)) 
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }
        AppUser u = new AppUser();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(password));
        u.setTokenVersion(0);
        repo.save(u);
        String token = jwtUtil.generateToken(username, u.getTokenVersion());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "message", "User created",
                    "token", token
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) 
    {
    	logger.info("Login API is called");
        String username = body.get("username");
        String password = body.get("password");
        AppUser u = repo.findById(username).orElse(null);
        if (u == null || !passwordEncoder.matches(password, u.getPasswordHash())) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(username, u.getTokenVersion());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(Principal principal) 
    {
    	logger.info("Logout API is called");
    	
    	
        if (principal == null) 
        {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        String username = principal.getName();
        AppUser u = repo.findById(username).orElse(null);
        if (u == null)
        	{
        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        	}
       
        u.setTokenVersion(u.getTokenVersion() + 1); // increment to invalidate old JWTs
        repo.save(u);
        return ResponseEntity.ok("Logged out");
    }
}







