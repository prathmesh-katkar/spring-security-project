package com.LightHouseLibrary.BookService.exception;

import lombok.Data;

@Data
public class BookNotFoundException extends RuntimeException {
	
	
	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
