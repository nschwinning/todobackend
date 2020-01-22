package com.schwinning.myapp.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.schwinning.myapp.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public Optional<User> findByName(@Param(value = "name") String name);
	
}
