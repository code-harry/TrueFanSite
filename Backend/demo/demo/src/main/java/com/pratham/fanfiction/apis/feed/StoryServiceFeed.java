//package com.pratham.fanfiction.apis.feed;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.pratham.fanfiction.mongo.Stories;
//import com.pratham.fanfiction.mongo.StoryRepository;
//
//@Service
//public class StoryServiceFeed 
//{
//	
//	@Autowired
//	private StoryRepository repo;
//	
//	
//	
//	List<Stories> get(int page,int size)
//	{
//		Pageable pageable = PageRequest.of(page, size);
//		Page<Stories> storyPage = repo.findAll(pageable);
//	    return storyPage.getContent();
//	}
//
//}
