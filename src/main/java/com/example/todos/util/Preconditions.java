package com.example.todos.util;

public class Preconditions {
	public static void checkArgument(boolean expression, Object errorMessage) {
		if(expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}
}
