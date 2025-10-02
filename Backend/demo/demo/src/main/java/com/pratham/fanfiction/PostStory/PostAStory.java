//package com.pratham.fanfiction.PostStory;
//
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.pratham.fanfiction.mongo.Stories;
//
//
//@RestController
//@CrossOrigin(origins = "*")
//public class PostAStory {
//
//    private static final Logger logger = LoggerFactory.getLogger(PostAStory.class);
//    private final StoryService storyService;
//
//    public PostAStory(StoryService storyService) {
//        this.storyService = storyService;
//    }
//
//    @PostMapping("/api/stories")
//    public ResponseEntity<String> postAStory(RequestEntity<Stories> requestEntity) {
//        logger.info("An API to post the story has been called.");
//
//        Stories s = requestEntity.getBody();
//        if (s == null) {
//            logger.warn("The request body is missing or invalid");
//            return ResponseEntity.badRequest().body("Request body is missing or invalid");
//        }
//
//        if (storyService.storyNameAlreadyPresent(s.getTitle())) {
//            logger.warn("This name cannot be used for a story: {}", s.getTitle());
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("This name cannot be used for a story");
//        }
//
//        storyService.saveStory(s);
//        logger.info("Story '{}' has been successfully saved", s.getTitle());
//        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
//    }
//}
