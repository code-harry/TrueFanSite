package com.pratham.fanfiction.apis.storyFetcherTitle;

import java.util.Optional;
import com.pratham.fanfiction.mongo.Stories;

public interface StoryService {
    Optional<Stories> getStoryByTitle(String title);
}