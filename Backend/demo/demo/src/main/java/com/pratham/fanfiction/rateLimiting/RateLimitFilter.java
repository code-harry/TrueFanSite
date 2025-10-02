//package com.pratham.fanfiction.rateLimiting;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Refill;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.Duration;
//// This rate limiting has been done according to the 
//
//
//@Component
//// A filter is an interface that performs performs filtering taks on the request or response to the server.
//public class RateLimitFilter implements Filter 
//{
//	// Represents the number of tokens. This buket will keep getting refilled according to the refil policy
//    private final Bucket bucket;
//
//    public RateLimitFilter() 
//    {
//    	 Allow 20 requests per minute per IP per API
//    	Old deprecated code
//        Bandwidth limit = Bandwidth.classic(20, Refill.greedy(20, Duration.ofMinutes(1)));
//        return Bucket.builder()
//                .addLimit(limit)
//                .build();
//    	
//    
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException 
//    {
//
//        if (bucket.tryConsume(1)) 
//        {
//            chain.doFilter(request, response);
//        } else 
//        {
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            httpServletResponse.setStatus(429); // Too Many Requests
//            httpServletResponse.setContentType("text/plain;charset=UTF-8");
//            httpServletResponse.getWriter().write("Too many requests - please try again later");
//        }
//    }
//}
