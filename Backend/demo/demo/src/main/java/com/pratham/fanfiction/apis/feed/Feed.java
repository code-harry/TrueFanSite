//package com.pratham.fanfiction.apis.feed;
//
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.pratham.fanfiction.mongo.Stories;
//
//
//@RestController
//@CrossOrigin(origins = "*")
//public class Feed 
//{
//
//	@Autowired
//	StoryServiceFeed feed;
//	
//	private static Logger logger = LoggerFactory.getLogger(Feed.class);
//	
//	@GetMapping("/api/stories")
//	public List<Stories> getStories(
//	    @RequestParam(defaultValue = "0") int page,
//	    @RequestParam(defaultValue = "10") int size) 
//	{
//		logger.info("Feed API was called");
//	    
//	    return feed.get(page,size);
//	}
//
//}
