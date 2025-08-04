package com.example.demo.apis;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Stories;
import com.example.demo.StoryRepository;

@RestController
@CrossOrigin(origins = "*")
public class FetchingStoryBasedOnTitle 
{
	
	
	@Autowired
	private StoryRepository repo;
	
	
	private static Logger logger =LoggerFactory.getLogger(FetchingStoryBasedOnTitle.class);
	
	
	@GetMapping("/api/story")
	public ResponseEntity<Stories> FetchStoryBasedOnTitle(@RequestParam(required=true) String title)
	{
		// 2. Perform the database lookup ONCE using findById, as 'title' is your @Id
        Optional<Stories> storyOptional = repo.findById(title);

        // 3. Process the result and return appropriate HTTP status
        if (storyOptional.isPresent()) {
            Stories foundStory = storyOptional.get();
            logger.info("Story found: {}", foundStory);
            logger.info(foundStory.getAge());
            logger.info(foundStory.getLanguage());
            // System.out.println(foundStory); // For console debugging
            return new ResponseEntity<>(foundStory, HttpStatus.OK); // 200 OK with the story data
        } else {
            logger.info("Story with title (ID) '{}' not found in the database. Returning 404 Not Found.", title);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
        
	}

}
