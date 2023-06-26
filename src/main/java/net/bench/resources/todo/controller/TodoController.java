package net.bench.resources.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.service.ITodoService;

@CrossOrigin(
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS},
		maxAge = 3600,
		allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
		origins = "*"
		)
@RestController
@RequestMapping(value = "/todos")
public class TodoController {

	@Autowired
	private ITodoService iTodoService;

	/**
	 * 1. returns Todo item based on the supplied Id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<TodoResponse> getTodoById(@PathVariable String id) {

		return ResponseEntity.ok(iTodoService.getTodoById(id));
	}

	/**
	 * 2. returns all Todo items
	 * 
	 * @return
	 */
	@GetMapping(value = "/")
	public ResponseEntity<List<TodoResponse>> getAllTodos() {

		return ResponseEntity.ok(iTodoService.getAllTodoList());
	}

	/**
	 * 3. inserts or add or creates new Todo item
	 * 
	 * @param todoRequest
	 * @return
	 */
	@PostMapping(value = "/")
	public ResponseEntity<TodoResponse> addTodo(@RequestBody TodoRequest todoRequest) {

		return ResponseEntity.ok(iTodoService.addTodo(todoRequest));
	}

	/**
	 * 4. partially updates Todo item based on the supplied Id
	 * 
	 * @param id
	 * @param todoRequest
	 * @return
	 */
	@PatchMapping(value = "/{id}")
	public ResponseEntity<TodoResponse> updateTodoById(@PathVariable String id, @RequestBody TodoRequest todoRequest) {

		return ResponseEntity.ok(iTodoService.updateTodoById(id, todoRequest));
	}

	/**
	 * 5. deletes Todo item based on the supplied Id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TodoResponse> deleteTodoById(@PathVariable String id) {

		return ResponseEntity.ok(iTodoService.deleteTodoById(id));
	}

	/**
	 * 6. deletes all Todo items
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/")
	public ResponseEntity<List<TodoResponse>> clearTodos() {

		return ResponseEntity.ok(iTodoService.clearTodos());
	}
}