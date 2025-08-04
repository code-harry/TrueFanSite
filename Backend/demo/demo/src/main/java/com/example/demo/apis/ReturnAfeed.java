package com.example.demo.apis;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
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
	public List<Stories> getStories(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size) {
	    
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Stories> storyPage = repo.findAll(pageable);
	    return storyPage.getContent();
	}

}
