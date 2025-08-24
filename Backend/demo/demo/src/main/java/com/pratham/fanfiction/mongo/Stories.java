package com.pratham.fanfiction.mongo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stories")  // Maps to 'stories' collection in MongoDB
public class Stories {

//    @Id
//    private String id;      // MongoDB document id

	@Id
    private String title;
    private String content;
    private String media;
    private String language;
    private String genre;
    private String age;
    
    // Constructors
    public Stories() 
    {
    	
    }

    public Stories(String title, String content, String category,String language,String genre,String age) 
    {
        this.title = title;
        this.content = content;
        this.media = category;
        this.language=language;
        this.genre=genre;
        this.age=age;
    }

    // Getters and setters
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }

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
    public void setContent(String content) { this.content = content; }

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

