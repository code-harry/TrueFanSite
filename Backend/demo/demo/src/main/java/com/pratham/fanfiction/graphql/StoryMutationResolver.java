//package com.pratham.fanfiction.graphql;
//
//
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//import graphql.kickstart.tools.GraphQLMutationResolver;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StoryMutationResolver implements GraphQLMutationResolver {
//
//    @Autowired
//    private StoryRepository repo;
//
//    public String postStory(String title, String content, String media, String language, String genre, String age) {
//        if (repo.findById(title).isPresent()) {
//            return "This name cannot be used for a story.";
//        }
//
//        Stories story = new Stories();
//        story.setTitle(title);
//        story.setContent(content);
//        story.setMedia(media);
//        story.setLanguage(language);
//        story.setGenre(genre);
//        story.setAge(age);
//
//        repo.save(story);
//        return "Story saved successfully.";
//    }
//}
