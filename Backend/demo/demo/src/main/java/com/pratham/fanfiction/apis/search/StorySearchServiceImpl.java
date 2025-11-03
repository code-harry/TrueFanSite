package com.pratham.fanfiction.apis.search;

import com.pratham.fanfiction.mongo.Stories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Scope("singleton") // explicit
public class StorySearchServiceImpl implements StorySearchService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Stories> searchStories(String title, String language, String genre, String media, String age) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        if (title != null && !title.isBlank()) {
            criteriaList.add(Criteria.where("title").regex(Pattern.quote(title), "i"));
        }
        if (language != null && !language.isBlank()) {
            criteriaList.add(Criteria.where("language").regex(Pattern.quote(language), "i"));
        }
        if (genre != null && !genre.isBlank()) {
            criteriaList.add(Criteria.where("genre").regex(Pattern.quote(genre), "i"));
        }
        if (media != null && !media.isBlank()) {
            criteriaList.add(Criteria.where("media").regex(Pattern.quote(media), "i"));
        }
        if (age != null && !age.isBlank()) {
            criteriaList.add(Criteria.where("age").regex(Pattern.quote(age), "i"));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        return mongoTemplate.find(query, Stories.class);
    }
}
