package com.pratham.fanfiction.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PAsswordEncoderTest 
{
	
	@Test
	public void test()
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		PAsswordEncoder p = new PAsswordEncoder();
		PasswordEncoder e = p.passwordEncoder();
//		String t = 
		assertEquals(encoder.encode("gecukwegikwegikugik"), e.encode("wcvikecgieugecuigce"));
		
	}

}
