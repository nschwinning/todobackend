package com.schwinning.myapp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.schwinning.myapp.entities.Amount;

public interface AmountRepository extends JpaRepository<Amount, Long>{
	
	@Query("SELECT a FROM Amount a WHERE a.user.name=:username AND a.dueDate>=:date")
	List<Amount> retrieveAmountsForUserAfter(@Param(value= "date") LocalDate date, @Param(value="username") String username);

	@Query("SELECT a FROM Amount a WHERE a.user.name=:username")
	List<Amount> retrieveAllAmountsForUser(@Param(value="username") String username);

}
