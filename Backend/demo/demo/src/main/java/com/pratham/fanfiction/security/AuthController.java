package com.pratham.fanfiction.security;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.fanfiction.sql.AppUserEntity;
import com.pratham.fanfiction.sql.AppUserEntityRepository;

import ch.qos.logback.classic.Logger;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;


// This defines APIS for login, signup, and logout
@RestController
@RequestMapping("/auth")
@Scope("singleton") // explicit
public class AuthController 
{

	
	// Used to get data from the database
//    @Autowired 
//    AppUserRepository repo;
    
    @Autowired
    AppUserEntityRepository repoSQL;
    
    
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

        //Old code with mongo
//        Optional<AppUser>listOfUsername = repo.findByUsername(username);
        
        
        //New code with SQL
        AppUserEntity ListOfUserNameSQL = repoSQL.findByUsername(username);
        
        //Old code with mongo
//        if(listOfUsername.isPresent())
//        {
//        	 return ResponseEntity.status(HttpStatus.CONFLICT)
//                     .body(Map.of(
//                         "message", "This username cannot be used"
//                     ));
//        }
        
        //New code with SQL
        if(ListOfUserNameSQL!=null)
        {
        	 return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body(Map.of(
                       "message", "This username cannot be used"
                   ));
        }
        
        
        //getting the password from the body
        String password = body.get("password");
        
//        //If the username with which the person is trying to sign up is already taken then it will send a conflict
//        if (repo.existsById(username)) 
//        {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken");
//        }
        
        //Creating a user
        //Old code with mongo
//        AppUser u = new AppUser();
        
        //New code with SQL
        AppUserEntity u = new AppUserEntity();
        
        //setting the username
        //Old code with mongo
//        u.setUsername(username);
        
        
        //New code with SQL
        u.setUsername(username);
        
        
        //Encoding the password
        String encodedPassword = passwordEncoder.encode(password);
        
        //Setting the password
        //Old code with mongo
//        u.setPasswordHash(encodedPassword);
        
        //New code with SQL
        u.setPasswordHash(encodedPassword);
        
        //Setting the token number as 0
        //Old code with mongo
//        u.setTokenVersion(0);
        
        
        //New code with SQL
        u.setTokenVersion(0);
        
        
        //Old code with mongo
//        u.setRole("USER");
        
        
        
        //New code with SQL
        u.setRole("ROLE_" + "USER");
        
        //Saving the user into the database.
        //Old code with mongo
//        repo.save(u);
        
       repoSQL.save(u);
        
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
        AppUserEntity u = repoSQL.findById(username).orElse(null);
        
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
        AppUserEntity u = repoSQL.findById(username).orElse(null);
        if (u == null)
        	{
        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        	}
        if (u.getTokenVersion() == Integer.MAX_VALUE) 
        {
            u.setTokenVersion(0); // or reset to 1
        } else {
            u.setTokenVersion(u.getTokenVersion() + 1);
        }
//        u.setTokenVersion(u.getTokenVersion() + 1); // increment to invalidate old JWTs
        repoSQL.save(u);
        return ResponseEntity.ok("Logged out");
    }
}







