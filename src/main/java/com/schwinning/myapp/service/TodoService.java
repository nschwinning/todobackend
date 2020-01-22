package com.schwinning.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schwinning.myapp.entities.Todo;
import com.schwinning.myapp.repositories.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserService userService;

	public List<Todo> getAllTodos(String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			return todoRepository.retrieveAllByUsername(username);
		}
		throw new IllegalStateException("Not allowed to access the requested resources");
	}

	public Optional<Todo> deleteTodoById(long id) {
		Optional<Todo> todo = findTodoById(id);
		if (todo.isPresent()) {
			todoRepository.deleteById(id);
		}
		return todo;
	}

	public Optional<Todo> findTodoById(long id) {
		return todoRepository.findById(id);
	}

	public Todo saveTodo(Todo todo, String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			todo.setUsername(username);
			todo = todoRepository.save(todo);
			return todo;
		}
		throw new IllegalStateException("Not allowed to save the requested resource. Username does not match the logged in user.");
	}

}
