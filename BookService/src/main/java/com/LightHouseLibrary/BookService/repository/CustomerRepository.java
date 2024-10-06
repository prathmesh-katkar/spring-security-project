package com.LightHouseLibrary.BookService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LightHouseLibrary.BookService.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmail(String email);

}
