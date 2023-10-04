package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfiguration {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception  {
		/*
		 * Custom Security Configuartion
		 */
		
		/*	http.authorizeHttpRequests()
		.requestMatchers("/myAccount", "/myLoan", "/myCards", "/myBalance").authenticated()
		.requestMatchers("/contact", "/notices")
		.permitAll()
		.and().httpBasic()
		.and().formLogin();
		return http.build();
   
	}	
	*/	
  // Configuartion to deny all the request
    http.authorizeHttpRequests().
    anyRequest()
    .denyAll()
    .and().formLogin()
    .and().httpBasic();
		
    return http.build();
	}
	
	
	//Configuration 

}
