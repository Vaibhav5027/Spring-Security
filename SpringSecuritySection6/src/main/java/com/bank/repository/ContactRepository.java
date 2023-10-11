package com.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	
}
