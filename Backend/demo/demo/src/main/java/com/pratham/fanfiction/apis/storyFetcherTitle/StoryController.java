package com.pratham.fanfiction.apis.storyFetcherTitle;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pratham.fanfiction.bulkhead.MyFallbackHandler;
import com.pratham.fanfiction.mongo.Stories;
import com.pratham.fanfiction.rateLimiting.RateLimitFilterAccToIpAndApi;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Scope("singleton") // explicit
public class StoryController 
{

    private final StoryService storyService;
    private final MyFallbackHandler fallbackHandler;
    public StoryController(StoryService storyService,MyFallbackHandler fallbackHandler) 
    {
        this.storyService = storyService;
        this.fallbackHandler = fallbackHandler;
    }
   
    private static final Logger logger = LoggerFactory.getLogger(StoryController.class);
 

    @GetMapping("/story")
//    @Cacheable(
//            value = "stories-search-title",
//            key = "{#title}",
//            unless = "#result.isEmpty()"
//        )
    @Bulkhead(name = "myServiceBulkhead", fallbackMethod = "fallbackProcess")
    @Retry(name = "paymentServiceRetry", fallbackMethod = "fallbackPayment")
    @CircuitBreaker(name = "paymentServiceCB", fallbackMethod = "fallbackPayment")
    public ResponseEntity<Stories> fetchStoryByTitle(@RequestParam String title) 
    {
    	
    	
        Optional<Stories> story = storyService.getStoryByTitle(title);

        return story.map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Local fallback that delegates to external class
    public ResponseEntity<String> fallbackProcess(Throwable t) 
    {
    	
//    	logger.atDebug()""
        return fallbackHandler.handleProcessFallback(t);
    }
    
    public ResponseEntity<Stories> fallbackPayment(String title, Throwable t) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(null); // or return an empty Stories object if you prefer
    }
    
}
