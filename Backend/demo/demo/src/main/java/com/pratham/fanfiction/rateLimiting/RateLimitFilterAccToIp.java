//package com.pratham.fanfiction.rateLimiting;
//
//
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
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class RateLimitFilterAccToIp implements Filter 
//{
//
//    // Store a bucket per IP address (thread-safe)
//    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
//
//    private Bucket newBucket() 
//    {
//        // Allow 50 requests per minute per IP
//        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(50, Duration.ofMinutes(1)));
//        return Bucket.builder()
//                .addLimit(limit)
//                .build();
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException 
//    {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String ip = httpRequest.getRemoteAddr();  // Extract client IP
//
//        // Get or create a bucket for this IP
//        Bucket bucket = cache.computeIfAbsent(ip, k -> newBucket());
//
//        if (bucket.tryConsume(1)) {
//            chain.doFilter(request, response);
//        } else {
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.setStatus(429); // Too Many Requests
//            httpResponse.setContentType("text/plain;charset=UTF-8");
//            httpResponse.getWriter().write("Too many requests from IP: " + ip);
//        }
//    }
//}
