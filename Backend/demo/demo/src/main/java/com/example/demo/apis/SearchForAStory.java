package com.example.demo.apis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Stories;
import com.example.demo.StoryRepository;



@CrossOrigin(origins = "*") // Or restrict to specific origin
@RestController
public class SearchForAStory 
{
	
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SearchForAStory.class);
	
	@Autowired
	private StoryRepository repo;
	
	



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
		
		// Checking if all fields have been given a value or not
		if(title!=null && language!=null && genre!=null && media!=null && age!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		            title, language, genre,media,age
		        );
		}
		
		// Checking if 4 fields have been given a value so there will be 5 like this.
		
		//age is not present in it
		if(title!=null && language!=null && genre!=null && media!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
				     title, language, genre, media);
		}
		
		//media is not present in it
		if(title!=null && language!=null && genre!=null && age!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
				     title, language, genre, age);
		}
		
		//age is not present in it
		if(title!=null && language!=null && age!=null && media!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
				     title,  language, genre, media);
		}
		
		//language is not present in it
		if(title!=null && age!=null && media!=null && genre!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
				     title,  genre,  age,  media);
		}
		
		
		// title is not present in it
		if(language!=null && genre!=null && media!=null && age!=null)
		{
			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
				     language, genre,  age,  media);
		}
		
		// If three fields are present
		// age and media are absent
		if(title!=null && language!=null && genre!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCase(title,language,genre);
		}
		
		
		// media and genre are absent
		if(title!=null &&  language!=null && age!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCase(title,language,age);
		}
		
		if(title!=null && language!=null && media!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndMediaContainingIgnoreCase(title,language,media);
		}
		
		
		
		
		if(title!=null && genre!=null && age!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase( title,genre,age);
		}
		
		
		
		if(title!=null && genre!=null && media!=null)
		{
			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(title, genre,media);
		}
		
		if(title!=null && age!=null && media!=null)
		{
			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(language,genre,age);
		}
		
		
		
		if(language!=null && genre!=null && media!=null)
		{
			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(language,genre,media);
		}
		
		if(language!=null && age!=null && media!=null)
		{
			return repo.findByLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(language, age, media);
		}
			
			if(genre!=null && age!=null && media!=null)
			{
				return repo.findByGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(genre,age,media);
			}
			
			
			
			
			// Two field are there if
			if(title!=null && language!=null)
			{
				return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCase(title, language);
			}
			
			
			if(title!=null && genre!=null)
			{
				return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCase(title,genre);
			}
			
			if(title!=null && age!=null)
			{
				return repo.findByTitleContainingIgnoreCaseAndAgeContainingIgnoreCase(title, age);
			}
			
			if(title!=null && media!=null)
			{
				return repo.findByTitleContainingIgnoreCaseAndMediaContainingIgnoreCase(title,media);
			}
			if(language!=null && genre!=null)
			{
				return repo.findByLanguageIgnoreCaseAndGenreIgnoreCase(language, genre);
			}
			if(language!=null && age!=null)
			{
				return repo.findByLanguageIgnoreCaseAndAgeContainingIgnoreCase(language, age);
			}
			
			
			if(language!=null && media!=null)
			{
				return repo.findByLanguageIgnoreCaseAndMediaContainingIgnoreCase(language, media);
			}
			if(genre!=null && age!=null)
			{
				return repo.findByGenreIgnoreCaseAndAgeContainingIgnoreCase(genre, age);
			}
			
			if(genre!=null && media!=null)
			{
				return repo.findByGenreIgnoreCaseAndMediaContainingIgnoreCase( genre, media);
			}
			
			
			if(age!=null && media!=null)
			{
				return repo.findByAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(age, media);
			}
			if(title!=null)
			{
				return repo.findByTitleContainingIgnoreCase(title);
			}
			
			if(language!=null)
			{
				return repo.findByLanguageIgnoreCase(language);
			}
			if(genre!=null)
			{
				return repo.findByGenreIgnoreCase(genre);
			}
			if(age!=null)
			{
				return repo.findByAgeContainingIgnoreCase( age);
			}
			if( media!=null)
			{
				return repo.findByMediaContainingIgnoreCase(media);
			}

			
			
			List<Stories>l = new ArrayList<>();
			return l;
//return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
//            title, language, genre,media,age
//        );
    }

	

//	@GetMapping("/hello-world")
//	public String hello()
//	{
//		return "Hello-World";
//	}
	
	
}
