package net.bench.resources.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Todo item found for the specified Id", code = HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TodoItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 729117135234292077L;

	private String message;
}