package com.example.todos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "builder")
public class Todo {
	private int userId;
	private int id;
	private String title;
	private boolean completed;
}