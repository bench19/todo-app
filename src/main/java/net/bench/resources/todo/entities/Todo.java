package net.bench.resources.todo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, unique =  true)
	private String item;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean completed;

	@Column(nullable = false)
	private long orderNumber;
}