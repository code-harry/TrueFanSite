package com.example.demo;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Stories, String> 
{
	// SINGLE FIELD
	List<Stories> findByTitleContainingIgnoreCase(String title);
	List<Stories> findByLanguageIgnoreCase(String language);
	List<Stories> findByGenreIgnoreCase(String genre);
	List<Stories> findByAgeContainingIgnoreCase(String age);
	List<Stories> findByMediaContainingIgnoreCase(String media);

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

	// FIVE FIELDS (Full Search)
	List<Stories> findByTitleContainingIgnoreCaseAndLanguageIgnoreCaseAndGenreIgnoreCaseAndAgeContainingIgnoreCaseAndMediaContainingIgnoreCase(
	    String title, String language, String genre, String age, String media);

}
