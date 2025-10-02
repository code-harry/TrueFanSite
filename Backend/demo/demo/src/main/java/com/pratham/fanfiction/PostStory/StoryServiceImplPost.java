//package com.pratham.fanfiction.PostStory;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//@Service
//public class StoryServiceImplPost implements StoryService {
//
//    private static final Logger logger = LoggerFactory.getLogger(StoryServiceImplPost.class);
//    private final StoryRepository repo;
//
//    public StoryServiceImplPost(StoryRepository repo) {
//        this.repo = repo;
//    }
//
//    @Override
//    public boolean storyNameAlreadyPresent(String title) {
//        logger.info("Checking if story already exists with title: {}", title);
//        return repo.findById(title).isPresent();
//    }
//
//    @Override
//    public void saveStory(Stories story) {
//        logger.info("Saving story: {}", story.getTitle());
//        repo.save(story);
//    }
//}
