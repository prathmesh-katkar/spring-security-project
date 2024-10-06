package com.LightHouseLibrary.BookService.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class LightHouseLibraryUserNamePwdAuthenticationProvider implements AuthenticationProvider{

	private final LightHouseLibraryUserDetailsService userDetailService;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userName= authentication.getName();
		
		String pwd = authentication.getCredentials().toString();
		
		log.info("UserName :"+userName);
		log.info("Password :"+pwd);
		
		UserDetails userDetails = userDetailService.loadUserByUsername(userName);
		
		if(passwordEncoder.matches(pwd, userDetails.getPassword())) {
			
			// You can provide any validation logic here as per client requirement
			return new UsernamePasswordAuthenticationToken(userName, pwd, userDetails.getAuthorities());			
			
		}else {
			throw new BadCredentialsException("Invalid password!");
		}
		
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
		
	}

	
	
}
