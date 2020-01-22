package com.schwinning.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schwinning.myapp.model.AuthenticationBean;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthenticationController {
	
	@GetMapping(path= "/basicauth")
	public AuthenticationBean authenticate() {
		return new AuthenticationBean("You are authenticated");
	}

}
