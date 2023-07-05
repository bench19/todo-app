package net.bench.resources.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bench.resources.todo.entities.Todo;
import net.bench.resources.todo.exception.TodoItemNotFoundException;
import net.bench.resources.todo.model.TodoRequest;
import net.bench.resources.todo.model.TodoResponse;
import net.bench.resources.todo.repositoy.TodoRepository;

@Service
public class TodoServiceImpl implements ITodoService {

	@Autowired
	private TodoRepository todoRepository;

	/**
	 * 1. get Todo by Id
	 */
	@Override
	public TodoResponse getTodoById(String id) {

		// local variable
		Todo todo = null;

		// find Todo item from DB
		Optional<Todo> optionalTodo = todoRepository.findById(Long.parseLong(id));

		if(optionalTodo.isPresent()) {

			todo = optionalTodo.get();

			// copy entity values into Todo Response
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);

			// return
			return todoResponse;

		} else {

			throw new TodoItemNotFoundException("Todo item not found in DB for retrieval");
		}
	}

	/**
	 * 2. get all Todos
	 */
	@Override
	public List<TodoResponse> getAllTodoList() {

		// find all Todo items from DB
		Iterable<Todo> todos = todoRepository.findAll();

		// copy entity values into Todo Response
		List<TodoResponse> responseTodos = new ArrayList<TodoResponse>();

		// iterate and set entity Todo to TodoResponse
		todos.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse(); 
			BeanUtils.copyProperties(todo, todoResponse);
			responseTodos.add(todoResponse);
		});

		// return
		return responseTodos;
	}

	/**
	 * 3. insert new Todo
	 */
	@Override
	public TodoResponse addTodo(TodoRequest todoVO) {

		// copy Todo request into Todo entity
		Todo todo = new Todo();
		BeanUtils.copyProperties(todoVO, todo);

		// save the Todo entity into DB
		todo = todoRepository.save(todo);

		// copy entity values into Todo Response
		TodoResponse todoResponse = new TodoResponse();
		BeanUtils.copyProperties(todo, todoResponse);

		// return
		return todoResponse;
	}

	/**
	 * 4. partial update of Todo by Id and request
	 */
	@Override
	public TodoResponse updateTodoById(String id, TodoRequest todoRequest) {

		// local variable
		Todo todo = null;

		// check whether Todo is present in the DB for the passed id
		Optional<Todo> optionalTodo = todoRepository.findById(Long.parseLong(id));

		if(optionalTodo.isPresent()) {

			todo = optionalTodo.get();

			// copy Todo Request into retrieved entity
			BeanUtils.copyProperties(todoRequest, todo);

			// save the updated Todo into DB
			todoRepository.save(todo);

			// copy retrieved/updated Todo entity into Todo Response
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);

			// return 
			return todoResponse;

		} else {

			throw new TodoItemNotFoundException("Todo item not found in DB for updation");
		}
	}

	/**
	 * 5. delete Todo by Id
	 */
	@Override
	public TodoResponse deleteTodoById(String id) {

		// local variable
		Todo todo = null;

		// check whether Todo is present in the DB for the passed id
		Optional<Todo> optionalTodo = todoRepository.findById(Long.parseLong(id));

		if(optionalTodo.isPresent()) {

			todo = optionalTodo.get();

			// delete Todo from DB
			todoRepository.delete(todo);

			// copy retrieved/updated Todo entity into Todo Response
			TodoResponse todoResponse = new TodoResponse();
			BeanUtils.copyProperties(todo, todoResponse);

			// return 
			return todoResponse;

		} else {

			throw new TodoItemNotFoundException("Todo item not found in DB for deletion");
		}
	}

	/**
	 * 6. clear all Todos
	 */
	@Override
	public List<TodoResponse> clearTodos() {

		// find all Todo items from DB
		Iterable<Todo> todos = todoRepository.findAll();

		// copy entity values into Todo Response
		List<TodoResponse> responseTodos = new ArrayList<TodoResponse>();

		// iterate and set entity Todo to TodoResponse
		todos.forEach(todo -> {
			TodoResponse todoResponse = new TodoResponse(); 
			BeanUtils.copyProperties(todo, todoResponse);
			responseTodos.add(todoResponse);
		});

		// finally delete all Todo
		todoRepository.deleteAll();

		// return
		return responseTodos;
	}
}