//package com.pratham.fanfiction.graphql;
//
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.apis.search.StorySearchService;
//import com.pratham.fanfiction.apis.storyFetcherTitle.StoryService;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//import graphql.kickstart.tools.GraphQLQueryResolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class StoryQueryResolver implements GraphQLQueryResolver 
//{
//
//    @Autowired
//    private StoryRepository repo;
//
//    @Autowired
//    private StoryService storyService;
//
//    @Autowired
//    private StorySearchService storySearchService;
//
//    public List<Stories> getStories(int page, int size) 
//    {
//        return repo.findAll(PageRequest.of(page, size)).getContent();
//    }
//
//    public Stories getStoryByTitle(String title) 
//    {
//        return storyService.getStoryByTitle(title).orElse(null);
//    }
//
//    public List<Stories> searchStories(String title, String language, String genre, String media, String age) {
//        return storySearchService.searchStories(title, language, genre, media, age);
//    }
//}
