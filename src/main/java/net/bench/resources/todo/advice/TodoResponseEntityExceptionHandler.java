package net.bench.resources.todo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.bench.resources.todo.exception.TodoItemNotFoundException;

@RestControllerAdvice
public class TodoResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(TodoItemNotFoundException.class)
	public final ResponseEntity<String> handleTodoItemNotFoundException(TodoItemNotFoundException ex, WebRequest webRequest) {

		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);	
	}
}