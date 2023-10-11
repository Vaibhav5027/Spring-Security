package com.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

	@GetMapping("/myLoan")
	public String getLoanDetails() {
		return "here are the loan details from DB";
	}
}
