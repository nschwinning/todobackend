package com.schwinning.myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.schwinning.myapp.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

	@Query("SELECT t FROM Todo t WHERE t.username=:username")
	List<Todo> retrieveAllByUsername(@Param(value = "username") String username);
	
}
