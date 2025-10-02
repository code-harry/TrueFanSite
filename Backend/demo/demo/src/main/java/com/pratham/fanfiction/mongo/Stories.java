package com.pratham.fanfiction.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")  // Maps to 'stories' collection in MongoDB
public class Stories
{



	@Id
    private String title;// The title of the story
	
    private String content;// The actual content of the story
    
    private String media;// Tells whether the story is based on a book, movie or a tv series
    
    private String language;// Tells what language the story is in
    
    private String genre;// Tells the genre of the story
    
    private String age;// Tells the age group this story is suitable for 
    
    private String summary;// Gives a small summary of the story
    
    private String username;// Tells the username of the user who posted the story
    
    
    // Constructors
    public Stories() 
    {
    	
    }

    public Stories(String title, String content, String category,String language,String genre,String age,String summary,String username) 
    {
        this.title = title;
        this.content = content;
        this.media = category;
        this.language=language;
        this.genre=genre;
        this.age=age;
        this.summary = summary;
        this.username = username;
    }

    // Getters and setters
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
    
    
    public void setUsername(String username)
    {
    	this.username = username;
    }
    public String getUsername(String username)
    {
    	return username;
    }
    
    public String getSummary()
    {
    	return this.summary;
    }
    
    
    public void setSummary(String summary)
    {
    	this.summary = summary;
    }
    
    
    
    

    public String getTitle() 
    {
    	return title; 
    	}
    
    public void setTitle(String title) 
    {
    	this.title = title;
    	}

    public String getContent() 
    {
    	return content; 
    }
    
    public void setContent(String content) 
    { 
    	this.content = content; 
    }

    public String getMedia() 
    {
    	return media; 
    }
    
    public void setMedia(String media) 
    {
    	this.media = media;
    }

	public String getLanguage() 
	{
		return language;
	}

	public void setLanguage(String language) 
	{
		this.language = language;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public String getAge() 
	{
		return age;
	}

	public void setAge(String age) 
	{
		this.age = age;
	}
	
	public String toString()
	{
		return getMedia() + getLanguage() + getGenre() + getAge() + getTitle() + getContent() ;
	}
}



