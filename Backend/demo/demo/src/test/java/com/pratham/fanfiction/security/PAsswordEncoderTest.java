package com.pratham.fanfiction.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PAsswordEncoderTest 
{
	
//	@Autowired
	private PasswordEncoder passwordEncod;
	
	
	@Before
	public void b()
	{
		System.out.println("Hello, the before attribute has been done");
		this.passwordEncod =  new BCryptPasswordEncoder();
	}
	
	@Test(timeout=1000)
	public void a()
	{
		String a = passwordEncod.encode("cbkjwdbkwe");
		assertEquals("cbkjwdbkwe","cbkjwdbkwe");
	}
	
}
