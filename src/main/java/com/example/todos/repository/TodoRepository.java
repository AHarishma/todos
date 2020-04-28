package com.example.todos.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.todos.domain.Todo;

@Repository
public class TodoRepository {
	@Autowired
	RestTemplate restTemplate;
	
	public List<Todo> getAllTodos() {
		ResponseEntity<List<Todo>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/todos", HttpMethod.GET, null, new ParameterizedTypeReference<List<Todo>>(){});
		return response.getBody();
	}

}
