
package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.AccountTransactions;
import com.bank.repository.AccountTransactionsRepository;

@RestController
public class BalanceController {
 @Autowired
 private AccountTransactionsRepository accountTransactionRepo;
	@GetMapping("/myBalance")
	public List<AccountTransactions> getBalanceDetails(@RequestParam int id) {
		List<AccountTransactions> accountTransactions = accountTransactionRepo.findByCustomerIdOrderByTransactionDtDesc(id);
	if(accountTransactions!=null) {
		return accountTransactions;
	}
	else {
		return null;
	}
	}
}
