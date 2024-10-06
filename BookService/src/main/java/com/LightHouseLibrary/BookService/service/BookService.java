package com.LightHouseLibrary.BookService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.LightHouseLibrary.BookService.exception.BookNotFoundException;
import com.LightHouseLibrary.BookService.model.Book;
import com.LightHouseLibrary.BookService.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository BookRepo;

	public ResponseEntity<List<Book>> getAllBooks() {

		List<Book> findAllBook = BookRepo.findAll();

		if (findAllBook.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(findAllBook, HttpStatus.OK);
	}

	public ResponseEntity<String> addBook(Book addBook) {

		Optional<Book> findById = BookRepo.findById(addBook.getBookId());

		if (findById.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book is already present in Library");

		BookRepo.save(addBook);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("http://localhost:8080/Book/get-Book" + addBook.getBookId());
	}

	public ResponseEntity<Book> updateBook(long id, Book updateBook) {

		Optional<Book> findById = BookRepo.findById(id);

		if (findById.isPresent()) {
			Book findBook = findById.get();

			findBook.setQuantity(updateBook.getQuantity());
			BookRepo.saveAndFlush(findBook);
			return new ResponseEntity<>(findBook, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> deleteBook(long id) {

		Optional<Book> findById = BookRepo.findById(id);

		if (findById.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Check ID, Book is not available");
		}

		BookRepo.delete(findById.get());

		return ResponseEntity.status(HttpStatus.OK).body("Book Removed Successfully");
	}

	public ResponseEntity<Book> getBookById(long id) {

		Optional<Book> findById = BookRepo.findById(id);

		if (findById.isPresent()) {
			return new ResponseEntity<>(findById.get(), HttpStatus.OK);
		}

		throw new BookNotFoundException("Requested Book does not exist");
//		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Book>> getBookByCategory(String category) {

		List<Book> findByCategory = BookRepo.findByCategory(category);

		if (findByCategory.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(findByCategory, HttpStatus.OK);
	}

	public ResponseEntity<String> buyBook(String name, int quantity) {

		List<Book> bookByName = BookRepo.findByName(name);

		if (bookByName.isEmpty()) {

			return new ResponseEntity<String>("Sorry...! Requested Book not found", HttpStatus.BAD_REQUEST);

		} else {

			Book reqBook = bookByName.get(0);

			int inventoryQuntity = reqBook.getQuantity();
			if (inventoryQuntity >= quantity) {

				int diff = inventoryQuntity - quantity;
				if (diff == 0)
					BookRepo.delete(reqBook);
				else {
					reqBook.setQuantity(diff);
					BookRepo.save(reqBook);
				}

				return new ResponseEntity<String>("Purchase Successfully done :) ", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("only " + (inventoryQuntity) + " copies are avilable",
						HttpStatus.BAD_REQUEST);
			}
		}

	}

}
