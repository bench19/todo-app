package net.bench.resources.todo.api;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.utils.TestUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestApiClient {

	private static RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

	// base URL for Todo application
	String BASE_URL = "http://localhost:8080/todos/";

	@Test()
	@Order(1)
	public void apiAddTodo() {

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// invoke POST API using RestTemplate
		TodoResponse todoResponse = restTemplate
				.postForObject(BASE_URL, todoRequest, TodoResponse.class, httpEntity);

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting
		Assertions.assertEquals(mockTodoResponse, todoResponse);
	}

	@Test
	@Order(2)
	public void apiGetAllTodos() {

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke GET API using RestTemplate
		ResponseEntity<List<TodoResponse>> responseEntity = restTemplate
				.exchange(BASE_URL, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<TodoResponse>>() {});

		// mock response for assertion
		List<TodoResponse> mockTodoResponseList = TestUtils.createTodoResponseList();

		// asserting
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(mockTodoResponseList.get(0), responseEntity.getBody().get(0));
	}

	@Test
	@Order(3)
	public void apiGetTodoById() {

		// GET URL
		String GET_API_URL = BASE_URL + "1";

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke GET API using RestTemplate
		TodoResponse todoResponse = restTemplate
				.getForObject(GET_API_URL, TodoResponse.class, httpEntity);

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting
		Assertions.assertEquals(mockTodoResponse, todoResponse);
	}

	@Test
	@Order(4)
	public void apiUpdateTodoById() {

		// PATCH URL
		String PATCH_API_URL = BASE_URL + "1";

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// request parameters
		TodoRequest todoRequest = TestUtils.createTodoRequest();

		// invoke GET API using RestTemplate
		TodoResponse todoResponse = restTemplate
				.patchForObject(PATCH_API_URL, todoRequest, TodoResponse.class, httpEntity);

		// mock response for assertion
		TodoResponse mockTodoResponse = TestUtils.createTodoResponse();

		// asserting
		Assertions.assertEquals(mockTodoResponse, todoResponse);
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

		// invoke DELETE API using RestTemplate
		restTemplate.delete(DELETE_API_URL, httpEntity);
	}

	@Test
	@Order(6)
	public void apiClearTodos() {

		// header parameters
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.parseMediaType("application/json;charset=UTF-8")));
		HttpEntity<String> httpEntity = new HttpEntity<String>("parameters", headers);

		// invoke DELETE API using RestTemplate
		restTemplate.delete(BASE_URL, httpEntity);
	}
}