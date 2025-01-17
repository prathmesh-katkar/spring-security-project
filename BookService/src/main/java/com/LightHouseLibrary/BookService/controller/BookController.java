package com.LightHouseLibrary.BookService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.LightHouseLibrary.BookService.model.Book;
import com.LightHouseLibrary.BookService.service.BookService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/get-book")
	public ResponseEntity<List<Book>> getAllBooks() {

		return bookService.getAllBooks();
	}

	@GetMapping("/get-book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable long id) {

		return bookService.getBookById(id);
	}

	@GetMapping("/get-book/category/{category}")
	public ResponseEntity<List<Book>> getBookByCategory(@PathVariable String category) {

		return bookService.getBookByCategory(category);
	}

	/*
	-------------------------------------------------------------------------------------------------------------------
	*/
	@PostMapping("/admin/add-book")
	public ResponseEntity<String> addBook(@RequestBody Book addBook) {

		log.info(addBook.getName()+" "+ addBook.getAuthor());
		
		return bookService.addBook(addBook);
	}

	@PutMapping("/admin/update-book")
	public ResponseEntity<Book> updateBook(@RequestParam long id, @RequestBody Book updateBook) {

		return bookService.updateBook(id, updateBook);
	}

	@DeleteMapping("/admin/delete-book")
	public ResponseEntity<String> deleteBook(@RequestParam long id) {

		return bookService.deleteBook(id);
	}

	/*
	-------------------------------------------------------------------------------------------------------------------
	*/
	
	@GetMapping("/buy-book")
	public ResponseEntity<String> buyBook(@RequestParam String name, @RequestParam int quantity) {
		
		return bookService.buyBook(name, quantity);
		
	}
}
