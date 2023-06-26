package net.bench.resources.todo.model;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Component
public class TodoRequest {

	// member variables
	private long id;
	private String item;
	@Schema(defaultValue = "false")
	private boolean completed;
	private long orderNumber;
}