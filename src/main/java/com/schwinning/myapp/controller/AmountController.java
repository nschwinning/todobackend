package com.schwinning.myapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.schwinning.myapp.entities.Amount;
import com.schwinning.myapp.service.AmountService;

@RestController
public class AmountController {
	
	private AmountService amountService;

	@Autowired
	public AmountController(AmountService amountService) {
		super();
		this.amountService = amountService;
	}

	@GetMapping("/user/{username}/amounts")
	public List<Amount> getAllAmounts(@PathVariable(value = "username") String username) {
		return amountService.getAllAmountsByUsername(username);
	}
	
	@GetMapping("/user/{username}/amounts/{id}")
	public ResponseEntity<Amount> getAmountById(@PathVariable(value = "username") String username, @PathVariable long id) {
		Optional<Amount> amountOpt = amountService.getAmountByIdAndUsername(id, username);
		if (amountOpt.isPresent()) {
			return ResponseEntity.ok(amountOpt.get());
		}
		return ResponseEntity.notFound().build();	
	}
	
	@PostMapping("/user/{username}/amounts")
	public ResponseEntity<Void> createAmount(@PathVariable(value = "username") String username, @RequestBody Amount amount) {
		amountService.saveAmount(amount, username);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(amount.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
 	
}
