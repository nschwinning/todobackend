package com.schwinning.myapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schwinning.myapp.entities.Amount;
import com.schwinning.myapp.repositories.AmountRepository;

@Service
public class AmountService {

	private AmountRepository amountRepository;
	private UserService userService;

	@Autowired
	public AmountService(AmountRepository amountRepository, UserService userService) {
		this.amountRepository = amountRepository;
		this.userService = userService;
	}

	public List<Amount> getAllAmountsByUsername(String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			return amountRepository.retrieveAllAmountsForUser(username);
		}
		throw new IllegalStateException("Not allowed to access the requested resources");
	}

	public Optional<Amount> getAmountByIdAndUsername(long id, String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			return amountRepository.findById(id);	
		}
		throw new IllegalStateException("Not allowed to access the requested resources");
	}

	public List<Amount> getAmountsByUsermame(int numberOfDays, String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			LocalDate dateAfter = LocalDate.now().minusDays(numberOfDays);
			return amountRepository.retrieveAmountsForUserAfter(dateAfter, username);
		}
		throw new IllegalStateException("Not allowed to access the requested resource");
	}

	public Amount saveAmount(Amount amount, String username) {
		if (userService.validateUsernameForLoggedInUser(username)) {
			return amountRepository.save(amount);
		}
		throw new IllegalStateException("Not allowed to save amount for this user.");
	}

}
