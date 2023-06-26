package net.bench.resources.todo.service;

import java.util.List;

import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;

public interface ITodoService {

	public TodoResponse getTodoById(String id);
	public List<TodoResponse> getAllTodoList();
	public TodoResponse addTodo(TodoRequest todoVO);
	public TodoResponse updateTodoById(String id, TodoRequest todoRequest);
	public TodoResponse deleteTodoById(String id);
	public List<TodoResponse> clearTodos();
}