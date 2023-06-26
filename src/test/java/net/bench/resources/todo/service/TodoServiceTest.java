package net.bench.resources.todo.service;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import net.bench.resources.todo.entities.Todo;
import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.repositoy.TodoRepository;
import net.bench.resources.todo.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

	@InjectMocks
	private TodoServiceImpl todoService;

	@Mock
	private TodoRepository todoRepository;

	// 1. test case for getTodoById
	@Test
	public void testGetTodoById() {

		// test data
		Optional<Todo> optionalTodo = TestUtils.createOptionalTodo();
		TodoResponse todoResponse = TestUtils.createTodoResponse();

		// mocking repository layer
		Mockito.when(todoRepository.findById(1L)).thenReturn(optionalTodo);

		// executing and asserting
		Assertions.assertEquals(todoResponse, todoService.getTodoById("1"));
	}

	// 2. test case for getAllTodoList
	@Test
	public void testGetAllTodoList() {

		// test data
		Iterable<Todo> todos = TestUtils.createIterableTodos();
		List<TodoResponse> todoResponses = TestUtils.createTodoResponseList();

		// mocking repository layer
		Mockito.when(todoRepository.findAll()).thenReturn(todos);

		// executing and asserting
		Assertions.assertEquals(todoResponses, todoService.getAllTodoList());
	}

	// 3. test case for addTodo
	@Test
	public void testAddTodo() {

		// test data
		Todo todo = TestUtils.createTodo();
		TodoRequest todoRequest = TestUtils.createTodoRequest();
		TodoResponse todoResponse = TestUtils.createTodoResponse();

		// mocking repository layer
		Mockito.when(todoRepository.save(todo)).thenReturn(todo);

		// executing and asserting
		Assertions.assertEquals(todoResponse, todoService.addTodo(todoRequest));
	}

	// 4. test case for updateTodoById
	@Test
	public void testUpdateTodoById() {

		// test data
		Optional<Todo> optionalTodo = TestUtils.createOptionalTodo();
		TodoRequest todoRequest = TestUtils.createTodoRequest();
		TodoResponse todoResponse = TestUtils.createTodoResponse();

		// mocking repository layer
		Mockito.when(todoRepository.findById(1L)).thenReturn(optionalTodo);

		// executing and asserting
		Assertions.assertEquals(todoResponse, todoService.updateTodoById("1", todoRequest));
	}

	// 5. test case for updateTodoById
	@Test
	public void testDeleteTodoById() {

		// test data
		Optional<Todo> optionalTodo = TestUtils.createOptionalTodo();
		TodoResponse todoResponse = TestUtils.createTodoResponse();

		// mocking repository layer
		Mockito.when(todoRepository.findById(1L)).thenReturn(optionalTodo);

		// executing and asserting
		Assertions.assertEquals(todoResponse, todoService.deleteTodoById("1"));
	}

	// 6. test case for clearTodos
	@Test
	public void testClearTodos() {

		// test data
		Iterable<Todo> todos = TestUtils.createIterableTodos();
		List<TodoResponse> todoResponses = TestUtils.createTodoResponseList();

		// mocking repository layer
		Mockito.when(todoRepository.findAll()).thenReturn(todos);

		// executing and asserting
		Assertions.assertEquals(todoResponses, todoService.clearTodos());
	}
}