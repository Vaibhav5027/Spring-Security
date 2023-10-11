package com.bank.controller;

import java.io.PrintStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Customer;
import com.bank.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@RestController
public class LoginController {

	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@PostMapping("/register")
	@Transactional
	ResponseEntity registerCustomer(@RequestBody Customer customer) {
		Customer savedCustomer=null;
		ResponseEntity responseEntity=null;
		String hashPwd = passEncoder.encode(customer.getPwd());
		customer.setPwd(hashPwd);
		savedCustomer=custRepo.save(customer);
		System.out.println(savedCustomer);
		System.out.println(savedCustomer.getId());
		if(savedCustomer.getId()>=1) {
			responseEntity=ResponseEntity
						   .status(HttpStatus.CREATED)
					       .body("User Deatails Saved To Repository");
					       
		}
		else {
			responseEntity=ResponseEntity
					   .status(HttpStatus.INTERNAL_SERVER_ERROR)
				       .body("failed to save customer details ");
			
		
		}
		
		return responseEntity;
	}
}
