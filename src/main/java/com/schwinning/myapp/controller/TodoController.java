package com.schwinning.myapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.schwinning.myapp.entities.Todo;
import com.schwinning.myapp.service.TodoService;

@CrossOrigin
@RestController
public class TodoController {
	
	private static Logger log = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoService;

	@GetMapping(value = "/user/{username}/todos")
	public List<Todo> getTodos(@PathVariable(value = "username") String username){
		return todoService.getAllTodos(username);
	}
	
	@GetMapping(value = "/user/{username}/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable(value = "username") String username, @PathVariable(value="id") long id){
		Optional<Todo> todo = todoService.findTodoById(id);
		if (todo.isPresent()) {
			return ResponseEntity.ok(todo.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value="/user/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable(value = "username") String username, @PathVariable(value="id") long id) {
		if (todoService.deleteTodoById(id)!=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping(value="/user/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable(value = "username") String username, @PathVariable(value="id") long id, @RequestBody Todo todo) {
		log.info("Updating todo: " + todo);
		todo = todoService.saveTodo(todo, username);
		return ResponseEntity.accepted().body(todo);
	}
	
	@PostMapping(value="/user/{username}/todos")
	public ResponseEntity<Void> createTodo(@PathVariable(value = "username") String username, @RequestBody Todo todo) {
		todoService.saveTodo(todo, username);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
