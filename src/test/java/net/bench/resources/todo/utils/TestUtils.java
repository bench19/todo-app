package net.bench.resources.todo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bench.resources.todo.entities.Todo;
import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;

public class TestUtils {

	/**
	 * returns single TodoResponse
	 * @return
	 */
	public static TodoResponse createTodoResponse() {

		TodoResponse todoResponse = new TodoResponse();
		todoResponse.setId(1);
		todoResponse.setItem("Cleaning");
		todoResponse.setCompleted(false);
		todoResponse.setOrderNumber(11);
		return todoResponse;
	}

	/**
	 * returns single TodoRequest
	 * @return
	 */
	public static TodoRequest createTodoRequest() {

		TodoRequest todoRequest = new TodoRequest();
		todoRequest.setId(1);
		todoRequest.setItem("Cleaning");
		todoRequest.setCompleted(false);
		todoRequest.setOrderNumber(11);
		return todoRequest;
	}

	/**
	 * returns List of TodoResponse
	 * @return
	 */
	public static List<TodoResponse> createTodoResponseList() {

		// list
		List<TodoResponse> todos = new ArrayList<>();

		// object 1
		TodoResponse todoResponse = new TodoResponse();
		todoResponse.setId(1);
		todoResponse.setItem("Cleaning");
		todoResponse.setCompleted(false);
		todoResponse.setOrderNumber(101);
		todos.add(todoResponse);

		// object 2
		todoResponse = new TodoResponse();
		todoResponse.setId(2);
		todoResponse.setItem("Cooking");
		todoResponse.setCompleted(false);
		todoResponse.setOrderNumber(201);
		todos.add(todoResponse);

		return todos;
	}

	/**
	 * returns single Optional<Todo>
	 * @return
	 */
	public static Optional<Todo> createOptionalTodo() {

		Todo todo = new Todo();
		todo.setId(1);
		todo.setItem("Cleaning");
		todo.setCompleted(false);
		todo.setOrderNumber(11);
		return Optional.of(todo);
	}

	/**
	 * returns single Todo
	 * @return
	 */
	public static Todo createTodo() {

		Todo todo = new Todo();
		todo.setId(1);
		todo.setItem("Cleaning");
		todo.setCompleted(false);
		todo.setOrderNumber(11);
		return todo;
	}

	/**
	 * returns Iterable of Todo
	 * @return
	 */
	public static Iterable<Todo> createIterableTodos() {

		// list
		List<Todo> todos = new ArrayList<>();

		// object 1
		Todo todo = new Todo();
		todo.setId(1);
		todo.setItem("Cleaning");
		todo.setCompleted(false);
		todo.setOrderNumber(101);
		todos.add(todo);

		// object 2
		todo = new Todo();
		todo.setId(2);
		todo.setItem("Cooking");
		todo.setCompleted(false);
		todo.setOrderNumber(201);
		todos.add(todo);

		return todos;
	}

	/**
	 * converts input objct into equivalent JSON string
	 * 
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	public static <T> String convertObjectToJson(T response) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(response);
	}
}