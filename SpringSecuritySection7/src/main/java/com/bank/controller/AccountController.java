package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Accounts;
import com.bank.repository.AccountsRepository;

@RestController
public class AccountController {
@Autowired
private AccountsRepository accRepo;
	@GetMapping("/myAccount")
	public Accounts getAccountDetails(@RequestParam int id) {
		Accounts accounts = accRepo.findByCustomerId(id);
  
		if(accounts!=null) {
			return accounts;
		}
		else {
			return null;
		}
		
	}
}
