package com.example.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.security.controller"})
public class BasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSecurityApplication.class, args);
	}

}
