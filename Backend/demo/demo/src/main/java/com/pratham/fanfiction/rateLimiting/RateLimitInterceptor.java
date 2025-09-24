package com.pratham.fanfiction.rateLimiting;

import io.github.bucket4j.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Component//Marks this class as a spring manages bean so that Spring can inject it automatically
public class RateLimitInterceptor implements HandlerInterceptor 
{
	//Cache to store a bucket(rate-limiter) for each client(keyed by IP address)
    private final ConcurrentHashMap<String, Bucket> cache = new ConcurrentHashMap<>();
    // A concurrent HashMap is a thread-safe implmentation of the map interface in Java which means multiple threads can access and modify it 
    //at the same time without causing data corruption or inconsistencies//It is a high performance alternative to the standard hashmap

    
    
    
    //Create a new bucket with rate limiting rules 
    private Bucket createNewBucket() 
    {
    	//
        Bandwidth limit = Bandwidth.classic
        		(10, // Allows 10 req/min
        		Refill.greedy(10, Duration.ofMinutes(1))); // Refills 10 tokens every minute
        return Bucket
        		.builder().addLimit(limit).build();
    }

    
    //This method runs before the controller handles the request
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception 
    {
    	
    	//Uses the client's IP as the key
        String ip = request.getRemoteAddr(); // or use userId / API key
        
        //Get the bucket from this cache, if not present create a new one.
        Bucket bucket = cache.computeIfAbsent(ip, k -> createNewBucket());
        
        
        //Try to consume one request from the bucket
        if (bucket.tryConsume(1)) 
        {
        	//If successfull,(bucket had tokens to spare), allow the request to proceed
            return true; // allow request
        } 
        else 
        {
        	// If no token is left, then rate limit exceeded
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Too many requests. Try again later.");
            return false;// by false it blocks the request
        }
    }
}
