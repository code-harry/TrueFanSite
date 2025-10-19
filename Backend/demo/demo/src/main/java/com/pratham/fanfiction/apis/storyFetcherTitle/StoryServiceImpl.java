package com.pratham.fanfiction.apis.storyFetcherTitle;



import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pratham.fanfiction.mongo.Stories;
import com.pratham.fanfiction.mongo.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService 
{

    private final StoryRepository repo;
    private static final Logger logger = LoggerFactory.getLogger(StoryServiceImpl.class);

    @Autowired
    public StoryServiceImpl(StoryRepository repo) 
    {
        this.repo = repo;
    }

    @Override
    public Optional<Stories> getStoryByTitle(String title) 
    {
        Optional<Stories> storyOptional = repo.findById(title);

        if (storyOptional.isPresent()) {
            logger.info("Story found: {}", storyOptional.get());
        } else {
            logger.info("Story with title '{}' not found.", title);
        }

        return storyOptional;
    }
}
