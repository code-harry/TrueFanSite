package com.example.demo.apis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Stories;
import com.example.demo.StoryRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReturnAfeed 
{

	@Autowired
	StoryRepository repo;
	
	private static Logger logger = LoggerFactory.getLogger(ReturnAfeed.class);
	
	@GetMapping("/api/stories")
	public List<Stories> giveAFeed()
	{
		return repo.findAll(); 
	}
}
