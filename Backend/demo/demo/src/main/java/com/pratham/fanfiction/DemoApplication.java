package com.pratham.fanfiction;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner
{

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	public static void main(String[] args) 
	{
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		// TODO Auto-generated method stub
		logger.info("Application is now up");
		
	}

}
