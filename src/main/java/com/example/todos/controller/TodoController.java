package com.example.todos.controller;

import java.util.Collections;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todos.domain.Todo;
import com.example.todos.service.TodoService;
import com.example.todos.util.Preconditions;

@RestController
public class TodoController {
	@Autowired
	TodoService todoService;
	
    @GetMapping(value = "/todo/{todoId}")
    public ResponseEntity<Object> getTodosById(@PathVariable String todoId) {
    	try {
	    	Preconditions.checkArgument(Strings.isBlank(todoId), "Todo Id is null or empty");
	    	int todoid;
	    	try {
	    		todoid = Integer.parseInt(todoId);
	    	}catch (NumberFormatException e) {
	    		throw new IllegalArgumentException("Invalid todo id");
	    	}
	        return new ResponseEntity<Object>(Collections.singletonMap("data", todoService.getTodosById(todoid)), HttpStatus.OK);
    	}catch (IllegalArgumentException e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.BAD_REQUEST);
    	}catch(Exception e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping(value = "/todos/user/userId/{userId}")
    public ResponseEntity<Object> getTodosByUserId(@PathVariable String userId) {
    	try {
	    	Preconditions.checkArgument(Strings.isBlank(userId), "User Id is null or empty");
	    	int userid;
	    	try {
	    		userid = Integer.parseInt(userId);
	    	}catch (NumberFormatException e) {
	    		throw new IllegalArgumentException("Invalid user id");
	    	}   
	        return new ResponseEntity<Object>(Collections.singletonMap("data", todoService.getTodosByUserId(userid)), HttpStatus.OK);
    	}catch (IllegalArgumentException e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.BAD_REQUEST);
    	}catch(Exception e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

       
    @GetMapping(value = "/todos/user/{userId}")
    public ResponseEntity<Object> getTodosByUserIdAndCompletedStatus(@PathVariable String userId, @RequestParam(required = false, defaultValue="not provided") String completed,
    		@RequestParam (required=false, defaultValue="") String id) {
    	try {
	    	Preconditions.checkArgument(Strings.isBlank(userId), "User Id is null or empty");
	    	int userid;
	    	try {
	    		userid = Integer.parseInt(userId);
	    	}catch (NumberFormatException e) {
	    		throw new IllegalArgumentException("Invalid user id");
	    	}
	    	Todo requestedTodo = Todo.builder().userId(userid).build();
	    	try {
	    		if(!"".equals(id)) 
	    			requestedTodo.setId(Integer.parseInt(id));
	    		else
	    			requestedTodo.setId(0);
	    	}catch(NumberFormatException e) {
	    		throw new IllegalArgumentException("Invalid id");
	    	}
	        return new ResponseEntity<Object>(Collections.singletonMap("data", todoService.getTodosByUserIdAndFilterByParam(requestedTodo, completed)), HttpStatus.OK);
    	}catch (IllegalArgumentException e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.BAD_REQUEST);
    	}catch(Exception e) {
    		return new ResponseEntity<Object>(Collections.singletonMap("message",e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }

}