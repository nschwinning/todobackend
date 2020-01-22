package com.schwinning.myapp.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schwinning.myapp.entities.User;
import com.schwinning.myapp.repositories.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByName(username);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}
		
		User user = userOptional.get();
		JwtUserDetails userDetails = new JwtUserDetails(user.getId(), user.getName(), user.getPassword(), user.getRole());

		return userDetails;
	}

}
