package com.pratham.fanfiction.mongo;

import java.util.List;

//import javax.management.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


//New code to find stories from a database
@Service//Service annotation means it is a spring managed bean containing business logic
public class StoryService 
{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Stories> search(String title, String language, String genre, 
                                String age, String media, String username) 
    {
        Query query = new Query();
        
        //If the story title is given then add it to the search criteria
        if (title != null) 
        {
            query.addCriteria(Criteria.where("title").regex(title, "i"));
        }
        
        //If language of the story is given then add it to the search criteria
        if (language != null) 
        {
            query.addCriteria(Criteria.where("language").is(language));
        }
        
        //If genre of the story is given then add it to the search criteria
        if (genre != null) 
        {
            query.addCriteria(Criteria.where("genre").is(genre));
        }
        
      //If age group of the story is given then add it to the search criteria
        if (age != null) 
        {
            query.addCriteria(Criteria.where("age").regex(age, "i"));
        }
        
      //If the media of the story is given then add it to the search criteria
        if (media != null) 
        {
            query.addCriteria(Criteria.where("media").regex(media, "i"));
        }
        
        //If the username/author of the story is given then add it to the search criteria
        if (username != null) 
        {
            query.addCriteria(Criteria.where("username").regex(username, "i"));
        }

        return mongoTemplate.find(query, Stories.class);
    }
}

