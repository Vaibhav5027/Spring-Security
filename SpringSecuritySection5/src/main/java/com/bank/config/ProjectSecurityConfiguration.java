package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfiguration {

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception  {
		/*
		 * Custom Security Configuartion
		 */
		
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/myAccount", "/myLoan", "/myCards", "/myBalance").authenticated()
		.requestMatchers("/contact", "/register","/notices")
		.permitAll()
		.and().httpBasic()
		.and().formLogin();
		return http.build();
   
	}	
	
  // Configuartion to deny all the request
 /*   http.authorizeHttpRequests().
    anyRequest()
    .denyAll()
    .and().formLogin()
    .and().httpBasic();
		
    return http.build();
	}
	
	
	//Configuration 




@Bean
public InMemoryUserDetailsManager userDetailsService() {
	/* Approach 1 Where we can user withDefaultPasswordEncoder 
	 * While Creating userDetails
	 */
	
	/*
	UserDetails admin=User.withDefaultPasswordEncoder()
			.username("admin")
			.password("12345")
			.authorities("admin")
			.build();
	
	UserDetails user=User.withDefaultPasswordEncoder()
			.username("user")
			.password("12345")
			.authorities("read")
			.build();
	
	return new InMemoryUserDetailsManager(admin,user);
	
	
	 Approach 2 Where we can user NoOpsPasswordEncoderBean 
	 While Creating userDetails
	
	
	UserDetails admin=User.withUsername("admin")
			.password("12345")
			.authorities("admin")
			.build();
	
	UserDetails user=User.withUsername("user")
			.password("12345")
			.authorities("read")
			.build();
	
	return new InMemoryUserDetailsManager(admin,user);
}





  no ops password encoder is not recommeded for production usage
 
	
	@Bean
	UserDetailsService userDetailsService(DataSource ds) {
		return new JdbcUserDetailsManager(ds);
	}
*/

@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

}	
	