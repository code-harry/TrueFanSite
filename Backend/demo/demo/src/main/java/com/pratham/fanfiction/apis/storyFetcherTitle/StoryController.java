package com.pratham.fanfiction.apis.storyFetcherTitle;



import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pratham.fanfiction.mongo.Stories;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/story")
//    @Cacheable(
//            value = "stories-search-title",
//            key = "{#title}",
//            unless = "#result.isEmpty()"
//        )
    public ResponseEntity<Stories> fetchStoryByTitle(@RequestParam String title) {
        Optional<Stories> story = storyService.getStoryByTitle(title);

        return story.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
