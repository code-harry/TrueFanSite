package com.pratham.fanfiction.bulkhead;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MyFallbackHandler 
{

    public ResponseEntity<String> handleProcessFallback(Throwable t) 
    {
        // You can customize based on exception type if needed
        return ResponseEntity
                .status(503)
                .body("Service temporarily busy. Please try again later.");
    }
}