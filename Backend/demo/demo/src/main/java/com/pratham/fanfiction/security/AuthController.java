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


// This defines APIS for login, signup, and logout
@RestController
@RequestMapping("/auth")
public class AuthController 
{

	
	// Used to get data from the database
    @Autowired 
    AppUserRepository repo;
    
    
    
    // Used to encode password
    @Autowired 
    PasswordEncoder passwordEncoder;
    
    
    // This will be used to generate and validate JWT Tokens
    @Autowired 
    JwtUtil jwtUtil;

    //Used to do logging
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    
    //API for signing up
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String,String> body) 
    {
    	logger.info("Signup API is called");
    	//getting the username from the body
        String username = body.get("username");
        
        //getting the password from the body
        String password = body.get("password");
        
        //If the username with which the person is trying to sign up is already taken then it will send a conflict
        if (repo.existsById(username)) 
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
        }
        
        //Creating a user
        AppUser u = new AppUser();
        
        //setting the username
        u.setUsername(username);
        
        //Encoding the password
        String encodedPassword = passwordEncoder.encode(password);
        
        //Setting the password
        u.setPasswordHash(encodedPassword);
        
        //Setting the token number as 0
        u.setTokenVersion(0);
        
        u.setRole("USER");
        
        //Saving the user into the database.
        repo.save(u);
        
        //generating the token
        String token = jwtUtil.generateToken(username, u.getTokenVersion());
        
        //Sending the response back with the JWT and a message stating that the user is created
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
    	// Getting the username from the request sent by the frontend
        String username = body.get("username");
        
        //Getting the password from the request sent by the frontend
        String password = body.get("password");
        
        // Finding in the database if the user is present by username or not
        AppUser u = repo.findById(username).orElse(null);
        
        // If there is no username or if the password of the user does not match then it will return
        if (u == null || !passwordEncoder.matches(password, u.getPasswordHash())) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        
        //Generate a token based on username and token version
        String token = jwtUtil.generateToken(username, u.getTokenVersion());
        
        
        
        // Return the request with the token
        return ResponseEntity.ok(Map.of("token", token));
    }

    
    
    
    //API for logging out
    @PostMapping("/logout")
    public ResponseEntity<?> logout(Principal principal) 
    {
    	logger.info("Logout API is called");
    	
    	//Principal means information about the currently logged in 
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







