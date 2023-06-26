package net.bench.resources.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Todo item found for the specified Id", code = HttpStatus.NOT_FOUND)
public class TodoItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 729117135234292077L;
}