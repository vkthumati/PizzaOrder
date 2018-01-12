package com.aquent.pizzaorder.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PizzaOrderLoggingAspect {
	
	@Around("execution(* com.aquent.pizzaorder.dao.impl.*.*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();
	    
	    Object proceed = joinPoint.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
	
}
