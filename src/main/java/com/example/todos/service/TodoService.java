package com.example.todos.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.todos.domain.Todo;
import com.example.todos.helper.TodoHelper;
import com.example.todos.repository.TodoRepository;

@Service
public class TodoService {
  @Autowired RestTemplate restTemplate;
  @Autowired TodoHelper todoHelper;
  @Autowired TodoRepository todoRepository;

  public Todo getTodosById(int todoId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    HttpEntity<Todo> entity = new HttpEntity<Todo>(headers);
    Todo result =
        restTemplate
            .exchange(
                "https://jsonplaceholder.typicode.com/todos/" + todoId,
                HttpMethod.GET,
                entity,
                Todo.class)
            .getBody();
    System.out.println("response ::" + result.toString());
    return result;
  }

  public List<Todo> getTodosByUserId(int todoId) {
    List<Todo> allTodos = todoRepository.getAllTodos();
    return todoHelper.getTodoByUserId(allTodos, todoId);
  }

  public List<Todo> getTodosByUserIdAndFilterByParam(Todo requestedTodo, String completed) {
    List<Todo> allTodos = todoRepository.getAllTodos();
    return allTodos
        .stream()
        .filter(
            todo -> {
              if (!"not provided".equals(completed) && requestedTodo.getId() != 0) {
                requestedTodo.setCompleted(Boolean.parseBoolean(completed));
                return requestedTodo.getUserId() == todo.getUserId()
                    && requestedTodo.isCompleted() == todo.isCompleted()
                    && requestedTodo.getId() == todo.getId();
              } else if ("not provided".equals(completed) && requestedTodo.getId() != 0) {
                return requestedTodo.getUserId() == todo.getUserId()
                    && requestedTodo.getId() == todo.getId();
              } else if (!"not provided".equals(completed) && requestedTodo.getId() == 0) {
                requestedTodo.setCompleted(Boolean.parseBoolean(completed));
                return requestedTodo.getUserId() == todo.getUserId()
                    && requestedTodo.isCompleted() == todo.isCompleted();
              } else return requestedTodo.getUserId() == todo.getUserId();
            })
        .collect(Collectors.toList());
  }
}
