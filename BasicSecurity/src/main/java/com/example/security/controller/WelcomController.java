package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

	@GetMapping("/welcome")
	public String sayWelcome() {
		return "Welcome to Spring application With Security";
	}
}
