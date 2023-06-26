package net.bench.resources.todo.repositoy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.bench.resources.todo.entities.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}