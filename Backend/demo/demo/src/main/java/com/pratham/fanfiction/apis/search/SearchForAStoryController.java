package com.pratham.fanfiction.apis.search;

import com.pratham.fanfiction.mongo.Stories;
import com.pratham.fanfiction.apis.search.StorySearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SearchForAStoryController {

    private static final Logger logger = LoggerFactory.getLogger(SearchForAStoryController.class);

    @Autowired
    private StorySearchService storySearchService;

    @GetMapping("/search")
    public List<Stories> searchStories(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String media,
            @RequestParam(required = false) String age
    ) {
        logger.info("Search API called");
        return storySearchService.searchStories(title, language, genre, media, age);
    }
}
