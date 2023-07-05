package net.bench.resources.todo.api;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.utils.TestUtils;
import reactor.core.publisher.Mono;

public class RestApiWebClient {

	// base URL for Todo application
	String BASE_URL = "http://localhost:8080/todos/";

	@Test()
	public void apiAddTodo() {

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke POST API using WebClient
		TodoResponse todoResponse = webClient
				.post()
				.uri(BASE_URL)
				.body(Mono.just(todoRequest), TodoRequest.class)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(TodoResponse.class)
				.block();

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting response
		Assertions.assertEquals(mockTodoResponse.getItem(), todoResponse.getItem());
	}

	@Test
	public void apiGetAllTodos() {

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke GET API using WebClient
		List<TodoResponse> todoResponseList = webClient
				.get()
				.uri(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<TodoResponse>>() {})
				.block();

		// mock response for assertion
		List<TodoResponse> mockTodoResponseList = TestUtils.createTodoResponseList();

		// asserting response
		Assertions.assertEquals(mockTodoResponseList.get(0).getItem(), todoResponseList.get(0).getItem());
	}

	@Test
	public void apiGetTodoById() {

		// GET URL
		String GET_API_URL = BASE_URL + "1";

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke GET API using WebClient
		TodoResponse todoResponse = webClient
				.get()
				.uri(GET_API_URL)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(TodoResponse.class)
				.block();

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting response
		Assertions.assertEquals(mockTodoResponse.getItem(), todoResponse.getItem());
	}

	@Test
	public void apiUpdateTodoById() {

		// PATCH URL
		String PATCH_API_URL = BASE_URL + "1";

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke PATCH API using WebClient
		TodoResponse todoResponse = webClient
				.patch()
				.uri(PATCH_API_URL)
				.body(Mono.just(todoRequest), TodoRequest.class)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(TodoResponse.class)
				.block();

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting response
		Assertions.assertEquals(mockTodoResponse.getItem(), todoResponse.getItem());
	}

	@Test
	public void apiDeleteTodoById() {

		// DELETE URL
		String DELETE_API_URL = BASE_URL + "1";

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke DELETE API using WebClient
		TodoResponse todoResponse = webClient
				.delete()
				.uri(DELETE_API_URL)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(TodoResponse.class)
				.block();

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting response
		Assertions.assertEquals(mockTodoResponse.getItem(), todoResponse.getItem());
	}

	@Test
	public void apiClearTodos() {

		// create WebClient
		WebClient webClient = WebClient.builder().build();

		// invoke DELETE API using WebClient
		List<TodoResponse> todoResponseList = webClient
				.delete()
				.uri(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<TodoResponse>>() {})
				.block();

		// asserting response
		Assertions.assertEquals(0, todoResponseList.size());
	}
}