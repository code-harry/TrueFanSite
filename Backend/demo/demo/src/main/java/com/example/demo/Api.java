package com.example.demo;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*") // Or restrict to specific origin
@RestController
public class Api 
{
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Api.class);
	
	@Autowired
	StoryRepository repo;
	
	
	@PostMapping("/api/stories")
	public String postAStory( @RequestBody Stories t)
	{

		logger.info("An API to post a story has been called");
		if(storyNameAlreadyPresent(t.getTitle())==true)
		{
			return "This name cannot be used for a story";
		}
		Stories s = new Stories(t.getTitle(),t.getContent(),t.getMedia(),t.getLanguage(),t.getGenre(),t.getAge());
//		if(repo.findById(t.getTitle()).isPresent())
//		{
//			System.out.println(repo.findById(t.getTitle()));
//			return "This name cannot be used for a story";
//		}
		repo.save(s);
		return "nbdks";
	}
	
	// This will return all the stories
	@GetMapping("/api/stories")
	public List<Stories> getAllStories() {
	    return repo.findAll();
	}
	@GetMapping("/api/story")
	public Stories getStoryByTitle(@RequestParam String title) {
	    return repo.findById(title).orElse(null);
	}

	@GetMapping("/api/search")
    public List<Stories> searchStories
    (
        @RequestParam(required = false) String title,
        @RequestParam(required = false) String language,
        @RequestParam(required = false) String genre,
        @RequestParam(required = false) String media,
        @RequestParam(required = false) String age
    ) 
	{
		logger.info("Search Api is Called****");
        if (title == null)
        	{
        	title = "";
        	}
        if (language == null) 
        {
        	language = "";
        }
        if (genre == null) 
        {
        	genre = "";
        }
        if(media == null)
        {
        	media = "";
        }
        if(age == null)
        {
        	age = "";
        }
return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
            title, language, genre,media,age
        );
    }

	private boolean storyNameAlreadyPresent(String t) 
	{
		if(repo.findById(t).isPresent())
		{
			System.out.println(repo.findById(t));
			return true;
		}
	return false;
	}

	@GetMapping("/hello-world")
	public String hello()
	{
		return "Hello-World";
	}
	
	
}
