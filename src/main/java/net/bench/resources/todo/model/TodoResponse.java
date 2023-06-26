package net.bench.resources.todo.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class TodoResponse {

	// member variables
	private long id;
	private String item;
	private boolean completed;
	private long orderNumber;
}