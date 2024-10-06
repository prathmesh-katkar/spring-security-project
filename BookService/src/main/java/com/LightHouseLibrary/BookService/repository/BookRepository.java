package com.LightHouseLibrary.BookService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LightHouseLibrary.BookService.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByCategory(String category);

	List<Book> findByName(String name);

}
