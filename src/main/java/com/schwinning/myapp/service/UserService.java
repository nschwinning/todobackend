package com.schwinning.myapp.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.schwinning.myapp.entities.User;
import com.schwinning.myapp.repositories.UserRepository;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;
	
	@PostConstruct
	public void init() {
		if (userRepo.count()==0) {
			createAdminUser();
		}
	}

	private void createAdminUser() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode("nils");
		User user = new User("nils", encodedPassword, "ROLE_USER_2");
		userRepo.save(user);
		log.info("Successfully created admin user with default password");
		
	}
	
	public boolean validateUsernameForLoggedInUser(String username) {
		return username.equals(getLoggedInUser().getName());
	}
	
	public User getLoggedInUser() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		Optional<User> userOpt = userRepo.findByName(username);
		if (userOpt.isPresent()) {
			return userOpt.get();
		}
		throw new IllegalStateException("Logged in user not found in database");
	}
	
}
