package com.pratham.fanfiction.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ApiCallsAspect 
{
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ApiCallsAspect.class);
	
	
	 // This pointcut will match any method in com.example.demo.apis or its sub-packages
    @Pointcut("execution(* com.example.demo.apis..*(..))")
    public void apiMethods() {}

    @Before("apiMethods()")
    public void logBefore(JoinPoint joinPoint) 
    {
//        System.out.println("Before method: " + joinPoint.getSignature().toShortString());
    	logger.info("An Api has been called");
    }

    @AfterReturning(pointcut = "apiMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) 
    {
//        System.out.println("After method: " + joinPoint.getSignature().toShortString() + ", returned: " + result);
    	logger.info("An Api has been called");
    }

    @AfterThrowing(pointcut = "apiMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) 
    {
//        System.out.println("Exception in method: " + joinPoint.getSignature().toShortString() + ", exception: " + ex.getMessage());
    	logger.info("An Api has been called");
    }
}
