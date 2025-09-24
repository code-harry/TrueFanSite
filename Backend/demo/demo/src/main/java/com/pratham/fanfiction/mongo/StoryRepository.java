package com.pratham.fanfiction.mongo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


//This is the old class used to find stories from a database
public interface StoryRepository extends MongoRepository<Stories, String> 
{
	// SINGLE FIELD
	List<Stories> findByTitleContainingIgnoreCase(String title);
	List<Stories> findByLanguageIgnoreCase(String language);
	List<Stories> findByGenreIgnoreCase(String genre);
	List<Stories> findByAgeContainingIgnoreCase(String age);
	List<Stories> findByMediaContainingIgnoreCase(String media);
	List<Stories> findByUsernameIgnoringCase(String username);

	// TWO FIELDS
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCase(String title, String language);
	List<Stories> findByTitleContainingIgnoreCaseAndGenreIgnoreCase(String title, String genre);
	List<Stories> findByTitleContainingIgnoreCaseAndAgeContainingIgnoreCase(String title, String age);
	List<Stories> findByTitleContainingIgnoreCaseAndMediaContainingIgnoreCase(String title, String media);

	List<Stories> findByLanguageIgnoreCaseAndGenreIgnoreCase(String language, String genre);
	List<Stories> findByLanguageIgnoreCaseAndAgeContainingIgnoreCase(String language, String age);
	List<Stories> findByLanguageIgnoreCaseAndMediaContainingIgnoreCase(String language, String media);

	List<Stories> findByGenreIgnoreCaseAndAgeContainingIgnoreCase(String genre, String age);
	List<Stories> findByGenreIgnoreCaseAndMediaContainingIgnoreCase(String genre, String media);

	List<Stories> findByAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(String age, String media);
	
	 List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCase(String username, String language);

	    List<Stories> findByUsernameIgnoreCaseAndGenreIgnoreCase(String username, String genre);

	    List<Stories> findByUsernameIgnoreCaseAndMediaContainingIgnoreCase(String username, String media);
	    
	    List<Stories> findByUsernameIgnoreCaseAndAgeContainingIgnoreCase(String username, String age);
	    
	    List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCase(String username, String title);


	    


	// THREE FIELDS
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCase(String title, String language, String genre);
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCase(String title, String language, String age);
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndMediaContainingIgnoreCase(String title, String language, String media);

	List<Stories> findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(String title, String genre, String age);
	List<Stories> findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(String title, String genre, String media);

	List<Stories> findByTitleContainingIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(String title, String age, String media);

	List<Stories> findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(String language, String genre, String age);
	List<Stories> findByLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(String language, String genre, String media);

	List<Stories> findByLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(String language, String age, String media);

	List<Stories> findByGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(String genre, String age, String media);
	
	List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCase(String username, String language, String genre);
	
	List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCase(
		    String username, String title, String language);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndGenreIgnoreCase(
		    String username, String title, String genre);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String title, String age);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String media);

		List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String language, String age);

		List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String language, String media);

		List<Stories> findByUsernameIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String genre, String age);

		List<Stories> findByUsernameIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String genre, String media);

		List<Stories> findByUsernameIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String age, String media);


	// FOUR FIELDS
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
	    String title, String language, String genre, String age);

	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
	    String title, String language, String genre, String media);

	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
	    String title, String language, String age, String media);

	List<Stories> findByTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
	    String title, String genre, String age, String media);

	List<Stories> findByLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
	    String language, String genre, String age, String media);
	
	List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
            String username, String language, String genre, String media);
	
	List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCase(
		    String username, String title, String language, String genre);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String title, String language, String age);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String language, String media);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String title, String genre, String age);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String genre, String media);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String age, String media);

		List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String language, String genre, String age);

		List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String language, String age, String media);

		List<Stories> findByUsernameIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String genre, String age, String media);


	// FIVE FIELDS (Full Search)
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
	    String title, String language, String genre, String age, String media);

	
	List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCase(
		    String username, String title, String language, String genre, String age);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String language, String genre, String media);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String language, String age, String media);

		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String title, String genre, String age, String media);

		List<Stories> findByUsernameIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
		    String username, String language, String genre, String age, String media);

		
		
		// 6 fields a full search
		
		List<Stories> findByUsernameIgnoreCaseAndTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
			    String username, String title, String language, String genre, String age, String media);

	
}
