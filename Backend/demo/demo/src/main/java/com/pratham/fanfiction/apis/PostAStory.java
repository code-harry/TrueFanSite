//This was the old code which did not use SOLID principals


package com.pratham.fanfiction.apis;

import java.lang.reflect.Type;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pratham.fanfiction.mongo.Stories;
import com.pratham.fanfiction.mongo.StoryRepository;

@RestController
@CrossOrigin(origins = "*")
@Scope("singleton")
public class PostAStory 
{
	
	@Autowired
	private StoryRepository repo;
	
	private static Logger logger = LoggerFactory.getLogger(PostAStory.class);
	
	
	public boolean storyNameAlreadyPresent(String t)
	{
		logger.info("Posting a story has been called");
		if(repo.findById(t).isPresent())
		{
			return true;
		}
		return false;
	}
	
	
	
//	@PostMapping("/api/stories")
//	public ResponseEntity<String> postAStory(RequestEntity<Stories> req)
//	{
//		logger.info("An Api to post the story has been called.");
//		Stories s = req.getBody();
//		HttpHeaders head =req.getHeaders();
//		HttpMethod method = req.getMethod();
//		URI url= req.getUrl();
//		Type type =  req.getType();
////		ResponseEntity<Stories> res = new ResponseEntity<Stories>(null);
//		
//		if(storyNameAlreadyPresent(s.getTitle())==true)
//		{
//			return ResponseEntity
//                    .status(HttpStatus.CONFLICT)
//                    .body("This name cannot be used for a story.");
//		}
//		
//		repo.save(s);
//		return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body("Story saved successfully.");
//	}
	
//	@PostMapping("/api/stories")
//	public String postAStory(@RequestBody  Stories s)
//	{
//		logger.info("An Api to post the story has been called.");
//		
//		if(storyNameAlreadyPresent(s.getTitle())==true)
//		{
//			return "This name cannot be used for a story";
//		}
//		
//		repo.save(s);
//		return "Ok";
//	}
	
	
	@PostMapping("/api/stories")
	public ResponseEntity<String> postAStory(RequestEntity<Stories> requestEntity) 
	{
	    logger.info("An API to post the story has been called.");

	    Stories s = requestEntity.getBody();

	    if (s == null) 
	    {
	    	logger.info("The request body is miissing or invalid");
	        return ResponseEntity.badRequest().body("Request body is missing or invalid");
	    }

	    if (storyNameAlreadyPresent(s.getTitle())) 
	    {
	    	logger.info("This name cannot be used for a story");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("This name cannot be used for a story");
	    }

	    repo.save(s);
	    logger.info("Story has been successfully saved");
	    return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
	}
	
	
	
	
	
	
//	@RequestMapping(path="/api/stories", method = RequestMethod.POST)
//	public String postAStory(@RequestBody Stories s)
//	{
//logger.info("An Api to post the story has been called.");
//		
//		if(storyNameAlreadyPresent(s.getTitle())==true)
//		{
//			return "This name cannot be used for a story";
//		}
//		
//		repo.save(s);
//		return "Ok";
//	}
}
