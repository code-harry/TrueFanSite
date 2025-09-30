//package com.pratham.fanfiction.rateLimiting;
//
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Refill;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.Duration;
//
//@Component
//public class RateLimitFilter implements Filter {
//
//    private final Bucket bucket;
//
//    public RateLimitFilter() {
//        // Allow 50 requests per second
//        Bandwidth limit = Bandwidth.classic(50, Refill.greedy(50, Duration.ofSeconds(1)));
//        this.bucket = Bucket.builder().addLimit(limit).build();
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        if (bucket.tryConsume(1)) {
//            chain.doFilter(request, response);
//        } else {
//            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//            httpServletResponse.setStatus(429); // Too Many Requests
//            httpServletResponse.getWriter().write("Too many requests - please try again later");
//        }
//    }
//}
