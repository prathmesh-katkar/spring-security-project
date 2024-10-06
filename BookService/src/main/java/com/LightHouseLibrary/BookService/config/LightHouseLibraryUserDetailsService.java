package com.LightHouseLibrary.BookService.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LightHouseLibrary.BookService.model.Customer;
import com.LightHouseLibrary.BookService.repository.CustomerRepository;

@Service
public class LightHouseLibraryUserDetailsService implements UserDetailsService{

	@Autowired
	private  CustomerRepository customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer = customerRepo.findByEmail(username).orElseThrow(()->
		 						new UsernameNotFoundException("User details not found for the user: " + username));
		
		List<GrantedAuthority> authorities =  List.of(new SimpleGrantedAuthority(customer.getRole()));
     
		return new User(customer.getEmail(), customer.getPassword(), authorities);
	}

}
