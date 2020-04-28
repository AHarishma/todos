package com.example.todos.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Log
@Aspect
@Component
public class TodoAspect {
	
	@Before("com.example.todos.aspect.PointcutConfigurations.todoServices()")  
	public void beforeAdvice(JoinPoint joinPoint) {  
		log.info("Entering the method ->" + joinPoint.getSignature());  
	}  
}