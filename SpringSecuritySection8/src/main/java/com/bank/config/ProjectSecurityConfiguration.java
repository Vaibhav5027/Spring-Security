package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bank.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Collections;

@Configuration
public class ProjectSecurityConfiguration {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");

		http.securityContext().requireExplicitSave(false).and()
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)).cors()
				.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(60L);
						return config;
					}
				}).and()
				.csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/contact", "/register")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class).authorizeHttpRequests()
				.requestMatchers("/myAccount").hasRole("USER")
				.requestMatchers("/myLoans").hasAnyRole("ADMIN", "USER")
				.requestMatchers("/myAccount").hasRole("USER")
				.requestMatchers("/myCards").hasRole("USER")
				.requestMatchers("/user").authenticated()
				.requestMatchers("/contact", "/register", "/notices")
				.permitAll().and().httpBasic().and().formLogin();
		return http.build();

	}

	// Configuartion to deny all the request
	/*
	 * http.authorizeHttpRequests(). anyRequest() .denyAll() .and().formLogin()
	 * .and().httpBasic();
	 * 
	 * return http.build(); }
	 * 
	 * 
	 * //Configuration
	 * 
	 * 
	 * 
	 * 
	 * @Bean public InMemoryUserDetailsManager userDetailsService() { /* Approach 1
	 * Where we can user withDefaultPasswordEncoder While Creating userDetails
	 */

	/*
	 * UserDetails admin=User.withDefaultPasswordEncoder() .username("admin")
	 * .password("12345") .authorities("admin") .build();
	 * 
	 * UserDetails user=User.withDefaultPasswordEncoder() .username("user")
	 * .password("12345") .authorities("read") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin,user);
	 * 
	 * 
	 * Approach 2 Where we can user NoOpsPasswordEncoderBean While Creating
	 * userDetails
	 * 
	 * 
	 * UserDetails admin=User.withUsername("admin") .password("12345")
	 * .authorities("admin") .build();
	 * 
	 * UserDetails user=User.withUsername("user") .password("12345")
	 * .authorities("read") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin,user); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * no ops password encoder is not recommeded for production usage
	 * 
	 * 
	 * @Bean UserDetailsService userDetailsService(DataSource ds) { return new
	 * JdbcUserDetailsManager(ds); }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
