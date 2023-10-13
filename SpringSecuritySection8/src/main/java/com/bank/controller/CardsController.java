package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Cards;
import com.bank.repository.CardsRepository;

@RestController
public class CardsController {
	@Autowired
	private CardsRepository cardRepo;
	@GetMapping("/myCards")
	public List<Cards> getCardsData(@RequestParam int id) {
       List<Cards> cards = cardRepo.findByCustomerId(id);       
       if (cards != null ) {
           return cards;
       }else {
           return null;
       }
	}
}
