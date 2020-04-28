package com.example.todos.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutConfigurations {
	@Pointcut("execution(* com.example.todos.service..*(..))")
	public void todoServices() { }
}
