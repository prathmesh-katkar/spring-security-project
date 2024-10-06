package com.LightHouseLibrary.BookService.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.LightHouseLibrary.BookService.exception.CustomBasicAuthenticationEntryPoint;
import com.LightHouseLibrary.BookService.filter.JWTTokenGenreatrorFilter;
import com.LightHouseLibrary.BookService.filter.JWTTokenValidatorFilter;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http
//		.requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // Only HTTPS request is accepted
				.csrf(csrfConfig -> csrfConfig.disable())
				.addFilterAfter(new JWTTokenGenreatrorFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/admin/add-book", "/admin/update-book", "/admin/delete-book").authenticated()
						.requestMatchers("/get-book/**", "/register").permitAll());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
//		http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); // this will consider only in login flow
		http.exceptionHandling(ehc -> ehc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())); // this
																												// will
																												// handle
																												// all
																												// possible
																												// flow
																												// where
																												// possibility
																												// of
																												// authentication
																												// failure

		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Simple encoder without compromised password checks
	}

	/**
	 * From Spring Security 6.3 version
	 * 
	 * @return
	 */
	/*
	 * @Bean public CompromisedPasswordChecker compromisedPasswordChecker() { return
	 * new HaveIBeenPwnedRestApiPasswordChecker(); }
	 */

}
