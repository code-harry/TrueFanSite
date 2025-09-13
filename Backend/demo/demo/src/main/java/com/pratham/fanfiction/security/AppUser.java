package com.pratham.fanfiction.security;

//import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


// This class represents a user that wants to login into the application which maps to a document called users in the mongo database.

@Document(collection = "users")
public class AppUser 
{
    @Id
    private String username; // assume unique username
    
    
    private String passwordHash;// Will contain the hashed password
    
    private String role; // e.g., "USER", "ADMIN"
    
    private int tokenVersion = 0; // for token invalidation by incrementing
    // add roles if needed
	public String getPasswordHash() 
	{
		// getting the password hash
		return this.passwordHash;
	}
	
	 public String getRole() {
	        return role;
	    }

	 public void setRole(String r)
	 {
		 this.role = r;
	 }
	
	
	public String getUsername() 
	{
		// getting the username 
		return this.username;
	}
	
	
	
	
	public int getTokenVersion() 
	{
		// getting the token version
		return this.tokenVersion;
	}
	
	
	
	
	
	
	public void setPasswordHash( String encode) 
	{
		// setting the password hash
		this.passwordHash = encode;
	}
	
	public void setUsername(String username2) 
	{
		// setting the username
		this.username = username2;
	}
	
	
	
	public void setTokenVersion(int i) 
	{
		// setting the token version
		this.tokenVersion = i;
		
	}
	
	

}