//package com.pratham.fanfiction.apis;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//
//
//@CrossOrigin(origins = "*") // Or restrict to specific origin
//@RestController
//public class SearchForAStory 
//{
//	
//	
//	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SearchForAStory.class);
//	
//	@Autowired
//	private StoryRepository repo;
//	
//	
//
//
//
//	@GetMapping("/api/search")
//    public List<Stories> searchStories
//    (
//        @RequestParam(required = false) String title,
//        @RequestParam(required = false) String language,
//        @RequestParam(required = false) String genre,
//        @RequestParam(required = false) String media,
//        @RequestParam(required = false) String age
//    ) 
//	{
//		logger.info("Search Api is Called****");
//		
//		// Checking if all fields have been given a value or not
//		if(title!=null && language!=null && genre!=null && media!=null && age!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
//		            title, language, genre,media,age
//		        );
//		}
//		
//		// Checking if 4 fields have been given a value so there will be 5 like this.
//		
//		//age is not present in it
//		if(title!=null && language!=null && genre!=null && media!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
//				     title, language, genre, media);
//		}
//		
//		//media is not present in it
//		if(title!=null && language!=null && genre!=null && age!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
//				     title, language, genre, age);
//		}
//		
//		//age is not present in it
//		if(title!=null && language!=null && age!=null && media!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
//				     title,  language, genre, media);
//		}
//		
//		//language is not present in it
//		if(title!=null && age!=null && media!=null && genre!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
//				     title,  genre,  age,  media);
//		}
//		
//		
//		// title is not present in it
//		if(language!=null && genre!=null && media!=null && age!=null)
//		{
//			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
//				     language, genre,  age,  media);
//		}
//		
//		// If three fields are present
//		// age and media are absent
//		if(title!=null && language!=null && genre!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCase(title,language,genre);
//		}
//		
//		
//		// media and genre are absent
//		if(title!=null &&  language!=null && age!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCase(title,language,age);
//		}
//		
//		if(title!=null && language!=null && media!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndMediaContainingIgnoreCase(title,language,media);
//		}
//		
//		
//		
//		
//		if(title!=null && genre!=null && age!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase( title,genre,age);
//		}
//		
//		
//		
//		if(title!=null && genre!=null && media!=null)
//		{
//			return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(title, genre,media);
//		}
//		
//		if(title!=null && age!=null && media!=null)
//		{
//			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(language,genre,age);
//		}
//		
//		
//		
//		if(language!=null && genre!=null && media!=null)
//		{
//			return repo.findByLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(language,genre,media);
//		}
//		
//		if(language!=null && age!=null && media!=null)
//		{
//			return repo.findByLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(language, age, media);
//		}
//			
//			if(genre!=null && age!=null && media!=null)
//			{
//				return repo.findByGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(genre,age,media);
//			}
//			
//			
//			
//			
//			// Two field are there if
//			if(title!=null && language!=null)
//			{
//				return repo.findByTitleContainingIgnoreCaseAndLanguageIgnoreCase(title, language);
//			}
//			
//			
//			if(title!=null && genre!=null)
//			{
//				return repo.findByTitleContainingIgnoreCaseAndGenreIgnoreCase(title,genre);
//			}
//			
//			if(title!=null && age!=null)
//			{
//				return repo.findByTitleContainingIgnoreCaseAndAgeContainingIgnoreCase(title, age);
//			}
//			
//			if(title!=null && media!=null)
//			{
//				return repo.findByTitleContainingIgnoreCaseAndMediaContainingIgnoreCase(title,media);
//			}
//			if(language!=null && genre!=null)
//			{
//				return repo.findByLanguageIgnoreCaseAndGenreIgnoreCase(language, genre);
//			}
//			if(language!=null && age!=null)
//			{
//				return repo.findByLanguageIgnoreCaseAndAgeContainingIgnoreCase(language, age);
//			}
//			
//			
//			if(language!=null && media!=null)
//			{
//				return repo.findByLanguageIgnoreCaseAndMediaContainingIgnoreCase(language, media);
//			}
//			if(genre!=null && age!=null)
//			{
//				return repo.findByGenreIgnoreCaseAndAgeContainingIgnoreCase(genre, age);
//			}
//			
//			if(genre!=null && media!=null)
//			{
//				return repo.findByGenreIgnoreCaseAndMediaContainingIgnoreCase( genre, media);
//			}
//			
//			
//			if(age!=null && media!=null)
//			{
//				return repo.findByAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(age, media);
//			}
//			if(title!=null)
//			{
//				return repo.findByTitleContainingIgnoreCase(title);
//			}
//			
//			if(language!=null)
//			{
//				return repo.findByLanguageIgnoreCase(language);
//			}
//			if(genre!=null)
//			{
//				return repo.findByGenreIgnoreCase(genre);
//			}
//			if(age!=null)
//			{
//				return repo.findByAgeContainingIgnoreCase( age);
//			}
//			if( media!=null)
//			{
//				return repo.findByMediaContainingIgnoreCase(media);
//			}
//
//			
//			
//			List<Stories>l = new ArrayList<>();
//			return l;
//    }
//
//	
//
////	@GetMapping("/hello-world")
////	public String hello()
////	{
////		return "Hello-World";
////	}
//	
//	
//}


//package com.pratham.fanfiction.apis;
//
//import com.pratham.fanfiction.mongo.Stories;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Pattern;
//
//@CrossOrigin(origins = "*")
//@RestController
//public class SearchForAStory {
//
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SearchForAStory.class);
//
//    // Inject MongoTemplate for building dynamic queries instead of the repository.
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    /**
//     * Searches for stories using a dynamic query built with MongoTemplate.
//     * This avoids the need for a complex chain of if-else statements.
//     *
//     * @param title    (optional) The title of the story to search for.
//     * @param language (optional) The language of the story.
//     * @param genre    (optional) The genre of the story.
//     * @param media    (optional) The media type (e.g., book, movie).
//     * @param age      (optional) The age rating of the story.
//     * @return A list of stories matching the provided criteria.
//     */
//    @GetMapping("/api/search")
//    public List<Stories> searchStories(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String language,
//            @RequestParam(required = false) String genre,
//            @RequestParam(required = false) String media,
//            @RequestParam(required = false) String age
//    ) {
//        logger.info("Search API is called with MongoTemplate");
//
//        // The Query object will hold all our search conditions.
//        Query query = new Query();
//        List<Criteria> criteriaList = new ArrayList<>();
//
//        // For each request parameter, if it's not null or empty, create a
//        // case-insensitive regex criterion and add it to our list.
//        if (title != null && !title.isBlank()) {
//            criteriaList.add(Criteria.where("title").regex(Pattern.quote(title), "i"));
//        }
//        if (language != null && !language.isBlank()) {
//            criteriaList.add(Criteria.where("language").regex(Pattern.quote(language), "i"));
//        }
//        if (genre != null && !genre.isBlank()) {
//            criteriaList.add(Criteria.where("genre").regex(Pattern.quote(genre), "i"));
//        }
//        if (media != null && !media.isBlank()) {
//            criteriaList.add(Criteria.where("media").regex(Pattern.quote(media), "i"));
//        }
//        if (age != null && !age.isBlank()) {
//            criteriaList.add(Criteria.where("age").regex(Pattern.quote(age), "i"));
//        }
//
//        // If any criteria were added, combine them all with an "AND" operator.
//        // This means a document must match ALL provided criteria to be returned.
//        if (!criteriaList.isEmpty()) {
//            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
//        }
//
//        // Execute the dynamically built query against the "stories" collection.
//        // If no criteria were provided, it will return all stories.
//        return mongoTemplate.find(query, Stories.class);
//    }
//}
