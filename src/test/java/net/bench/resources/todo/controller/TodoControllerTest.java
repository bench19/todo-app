package net.bench.resources.todo.controller;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.bench.resources.todo.exception.TodoItemNotFoundException;
import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.service.ITodoService;
import net.bench.resources.todo.utils.TestUtils;

@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = TodoController.class)
public class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ITodoService iTodoService;

	// 1. test case for get Todo by Id
	@Test
	public void testGetTodoById() throws Exception {

		// URI
		String uri = "/todos/1";

		// TodoResponse object
		TodoResponse response = TestUtils.createTodoResponse();

		// TodoResponse in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(response);

		// mock service layer
		Mockito.when(iTodoService.getTodoById("1")).thenReturn(response);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}

	// 1.B negative/exception test case for get Todo by Id
	@Test
	public void testGetTodoById_exception() throws Exception {

		// URI
		String uri = "/todos/1";

		// mock service layer
		Mockito.when(iTodoService.getTodoById("1")).thenThrow(TodoItemNotFoundException.class);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TodoItemNotFoundException));
	}

	// 2. test case for get all Todo list
	@Test
	public void testGetAllTodos() throws Exception {

		// URI
		String uri = "/todos/";

		// list of Todos
		List<TodoResponse> list = TestUtils.createTodoResponseList();

		// list of Todos in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(list);

		// mock service layer
		Mockito.when(iTodoService.getAllTodoList()).thenReturn(list);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}

	// 3. test case to insert single Todo
	@Test
	public void testAddTodo() throws Exception {

		// URI
		String uri = "/todos/";

		// request and response
		TodoRequest request = TestUtils.createTodoRequest();
		TodoResponse response = TestUtils.createTodoResponse();

		// request in json
		String jsonRequest = TestUtils.convertObjectToJson(request);

		// TodoResponse in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(response);

		// mock service layer
		Mockito.when(iTodoService.addTodo(request)).thenReturn(response);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType("application/json;charset=UTF-8").content(jsonRequest))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}

	// 4. test case for update Todo by Id
	@Test
	public void testUpdateTodoById() throws Exception {

		// URI
		String uri = "/todos/1";

		// request and response
		TodoRequest request = TestUtils.createTodoRequest();
		TodoResponse response = TestUtils.createTodoResponse();

		// request in json
		String jsonRequest = TestUtils.convertObjectToJson(request);

		// TodoResponse in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(response);

		// mock service layer
		Mockito.when(iTodoService.updateTodoById("1", request)).thenReturn(response);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.patch(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType("application/json;charset=UTF-8").content(jsonRequest))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}

	// 4.B negative/exception test case for update Todo by Id
	@Test
	public void testUpdateTodoById_exception() throws Exception {

		// URI
		String uri = "/todos/1";

		// request object
		TodoRequest request = TestUtils.createTodoRequest();

		// request json
		String jsonRequest = TestUtils.convertObjectToJson(request);

		// mock service layer
		Mockito.when(iTodoService.updateTodoById("1", request)).thenThrow(TodoItemNotFoundException.class);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.patch(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType("application/json;charset=UTF-8").content(jsonRequest))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TodoItemNotFoundException));
	}

	// 5. test case for delete Todo by Id
	@Test
	public void testDeleteTodoById() throws Exception {

		// URI
		String uri = "/todos/1";

		// request and response
		TodoRequest request = TestUtils.createTodoRequest();
		TodoResponse response = TestUtils.createTodoResponse();

		// request in json
		String jsonRequest = TestUtils.convertObjectToJson(request);

		// TodoResponse in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(response);

		// mock service layer
		Mockito.when(iTodoService.deleteTodoById("1")).thenReturn(response);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType("application/json;charset=UTF-8").content(jsonRequest))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}

	// 5.B negative/exception test case for delete Todo by Id
	@Test
	public void testDeleteTodoById_exception() throws Exception {

		// URI
		String uri = "/todos/1";

		// request object
		TodoRequest request = TestUtils.createTodoRequest();

		// request json
		String jsonRequest = TestUtils.convertObjectToJson(request);

		// mock service layer
		Mockito.when(iTodoService.deleteTodoById("1")).thenThrow(TodoItemNotFoundException.class);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.contentType("application/json;charset=UTF-8").content(jsonRequest))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof TodoItemNotFoundException));
	}

	// 6. test case for delete all Todo list
	@Test
	public void testClearTodos() throws Exception {

		// URI
		String uri = "/todos/";

		// list of Todos
		List<TodoResponse> list = TestUtils.createTodoResponseList();

		// list of Todos in JSON format for assertion
		String jsonContent = TestUtils.convertObjectToJson(list);

		// mock service layer
		Mockito.when(iTodoService.clearTodos()).thenReturn(list);

		// executing and asserting
		this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
		.andExpect(MockMvcResultMatchers.content().json(jsonContent));
	}
}