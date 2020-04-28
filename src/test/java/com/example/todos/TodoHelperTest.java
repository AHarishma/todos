package com.example.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.todos.domain.Todo;
import com.example.todos.helper.TodoHelper;

@RunWith(MockitoJUnitRunner.class)
public class TodoHelperTest {
	List<Todo> allTodos = new ArrayList<>();
	List<Todo> result = new ArrayList<>();
	
	TodoHelper todoHelper = mock(TodoHelper.class);
	
	@Before
	public void setup() {
		allTodos.add(new Todo(1,1,"todoTitle1",true));
		allTodos.add(new Todo(2,2,"todoTitle2",false));	
		result.add(new Todo(1,1,"todoTitle1",true));
		when(todoHelper.getTodoByUserId(allTodos,1)).thenReturn(result);
	}
	
	@Test
	public void testGetTodosByUserId() {
        assertEquals(allTodos, todoHelper.getTodoByUserId(allTodos,1));
	}
}
