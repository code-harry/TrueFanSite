package com.example.demo.apis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Stories;
import com.example.demo.StoryRepository;

@RestController
@CrossOrigin(origins = "*")
public class PostAStory 
{
	
	@Autowired
	private StoryRepository repo;
	
	private static Logger logger = LoggerFactory.getLogger(PostAStory.class);
	
	
	public boolean storyNameAlreadyPresent(String t)
	{
		if(repo.findById(t).isPresent())
		{
			return true;
		}
		return false;
	}
	
	
	@PostMapping("/api/stories")
	public String postAStory(@RequestBody  Stories s)
	{
		logger.info("An Api to post the story has been called.");
		
		if(storyNameAlreadyPresent(s.getTitle())==true)
		{
			return "This name cannot be used for a story";
		}
		
		repo.save(s);
		return "Ok";
	}
}
