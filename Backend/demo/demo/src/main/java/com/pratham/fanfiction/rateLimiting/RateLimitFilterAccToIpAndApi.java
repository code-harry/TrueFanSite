package com.pratham.fanfiction.rateLimiting;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.pratham.fanfiction.apis.storyFetcherTitle.StoryServiceImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton") // explicit
public class RateLimitFilterAccToIpAndApi implements Filter 
{

    // Key: ip + api path -> Bucket
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilterAccToIpAndApi.class);
    
    private Bucket newBucket() 
    {
        // Allow 20 requests per minute per IP per API
    	//Old deprecated code
//        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
//        return Bucket.builder()
//                .addLimit(limit)
//                .build();
    	
    	//New Code
    	 Bandwidth limit = Bandwidth.builder()
    	            .capacity(20)                                   // bucket capacity
    	            .refillIntervally(20, Duration.ofMinutes(1))    // refill 20 tokens every 1 minute
    	            .build();

    	    return Bucket.builder()
    	            .addLimit(limit)
    	            .build();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException 
    {

    	logger.info("Request is going through te Limiting for now");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ip = httpRequest.getRemoteAddr();
        String path = httpRequest.getRequestURI();

        // Composite key: IP + API path
        String key = ip + ":" + path;

        // Get or create a bucket for this (IP + API)
        Bucket bucket = cache.computeIfAbsent(key, k -> newBucket());

        if (bucket.tryConsume(1)) 
        {
            chain.doFilter(request, response);
        } 
        else 
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(429); // Too Many Requests
            httpResponse.setContentType("text/plain;charset=UTF-8");
            httpResponse.getWriter().write(
                "Too many requests from IP " + ip + " to API " + path
            );
        }
    }
}
