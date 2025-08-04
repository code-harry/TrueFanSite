package com.example.demo;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User 
{
	@Id
	private String username;
	private String password;
	public String getUsername() 
	{
		// TODO Auto-generated method stub
		return username;
	}
	public @Nullable String getPassword() 
	{
		// TODO Auto-generated method stub
		return password;
	}
	public void setPassword(@Nullable String encode) 
	{
		// TODO Auto-generated method stub
		this.password = encode;
		
	}
	
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	
}
