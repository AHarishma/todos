package com.example.todos.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.todos.domain.Todo;

@Component
public class TodoHelper {
	public List<Todo> getTodoByUserId(List<Todo> allTodos, int todoId) {
		 return allTodos
	        .stream()
	        .filter(todo -> todo.getUserId() == todoId)
	        .collect(Collectors.toList());
		}
}
