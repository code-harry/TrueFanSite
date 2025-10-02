
package com.pratham.fanfiction.apis.search;

import com.pratham.fanfiction.mongo.Stories;
import java.util.List;

public interface StorySearchService {
    List<Stories> searchStories(String title, String language, String genre, String media, String age);
}
