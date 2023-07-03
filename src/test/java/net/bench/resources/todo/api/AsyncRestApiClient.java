package net.bench.resources.todo.api;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.utils.TestUtils;

@SuppressWarnings("deprecation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AsyncRestApiClient {

	private static AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(new HttpComponentsAsyncClientHttpRequestFactory());

	// base URL for Todo application
	String BASE_URL = "http://localhost:8080/todos/";

	@Test()
	@Order(1)
	public void apiAddTodo() throws InterruptedException, ExecutionException {

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<TodoRequest> httpEntity = new HttpEntity<TodoRequest>(todoRequest, headers);



		// invoke POST API using AsyncRestTemplate
		ListenableFuture<ResponseEntity<TodoResponse>> future = asyncRestTemplate
				.exchange(BASE_URL, HttpMethod.POST, httpEntity, TodoResponse.class);

		// print response entity
		System.out.println("ResponseEntity :- " + future.get());

		// asserting status
		Assertions.assertEquals(HttpStatus.OK, future.get().getStatusCode());
	}

	@Test
	@Order(2)
	public void apiGetAllTodos() throws InterruptedException, ExecutionException {

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke GET API using AsyncRestTemplate
		ListenableFuture<ResponseEntity<List<TodoResponse>>> future = asyncRestTemplate
				.exchange(BASE_URL, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoResponse>>() {});

		// print response entity
		System.out.println("ResponseEntity :- " + future.get());

		// asserting status
		Assertions.assertEquals(HttpStatus.OK, future.get().getStatusCode());
	}

	@Test
	@Order(3)
	public void apiGetTodoById() throws InterruptedException, ExecutionException {

		// GET URL
		String GET_API_URL = BASE_URL + "1";

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke GET API using AsyncRestTemplate
		ListenableFuture<ResponseEntity<TodoResponse>> future = asyncRestTemplate
				.getForEntity(GET_API_URL, TodoResponse.class, httpEntity);

		// print response entity
		System.out.println("ResponseEntity :- " + future.get());

		// asserting status
		Assertions.assertEquals(HttpStatus.OK, future.get().getStatusCode());
	}

	@Test
	@Order(4)
	public void apiUpdateTodoById() throws InterruptedException, ExecutionException {

		// PATCH URL
		String PATCH_API_URL = BASE_URL + "1";

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<TodoRequest> httpEntity = new HttpEntity<TodoRequest>(todoRequest, headers);

		// invoke GET API using AsyncRestTemplate
		ListenableFuture<ResponseEntity<TodoResponse>> future = asyncRestTemplate
				.exchange(PATCH_API_URL, HttpMethod.PATCH, httpEntity, TodoResponse.class);

		// print response entity
		System.out.println("ResponseEntity :- " + future.get());

		// asserting status
		Assertions.assertEquals(HttpStatus.OK, future.get().getStatusCode());
	}

	@Test
	@Order(5)
	public void apiDeleteTodoById() {

		// DELETE URL
		String DELETE_API_URL = BASE_URL + "1";

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke DELETE API using AsyncRestTemplate
		asyncRestTemplate.delete(DELETE_API_URL, httpEntity);
	}

	@Test
	@Order(6)
	public void apiClearTodos() {

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke DELETE API using AsyncRestTemplate
		asyncRestTemplate.delete(BASE_URL, httpEntity);
	}
}