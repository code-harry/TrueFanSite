package com.pratham.fanfiction.security;

//JwtUtil.java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil 
{
 private final Key key = Keys.hmacShaKeyFor("replace-this-with-a-very-long-secret-key-of-at-least-256-bits".getBytes());// Consider it an encryption key
 
 
 
 private final long expirationMs = 15 * 60 * 1000*1000; // 15 minutes; Time for which the key is valid

 
 
 // Used to generate a JWT Token
 public String generateToken(String username, int tokenVersion) 
 {
     Date now = new Date(); // Getting the details of the current time
     Date exp = new Date(now.getTime() + expirationMs);// The time at which the token will expire

//     return Jwts.builder()
//             .setSubject(username)
//             .setIssuedAt(now)
//             .setExpiration(exp)
//             .claim("tv", tokenVersion) // tokenVersion claim
//             .compact();
     
     
     return Jwts.builder()
    		    .setSubject(username)
    		    .setIssuedAt(now)
    		    .setExpiration(exp)
    		    .claim("tv", tokenVersion)
    		    .signWith(key, SignatureAlgorithm.HS256) // <-- make sure to sign
    		    .compact();

     
 }

 
 //Used to validate a JWT Token
 public boolean validateToken(String token) 
 {
     try 
     {
         Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
         return true;
     } 
     catch (JwtException ex) 
     {
         return false;
     }
 }

 // If the claim is valid, this retrieves all the data stored inside
 public Claims getClaims(String token) 
 {
     return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
     
     // This function returns all the claims from the token if it is valid.
 }
 
 
 
 

}
