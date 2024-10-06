package com.LightHouseLibrary.BookService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {

	@ExceptionHandler(value = {BookNotFoundException.class})
	public ResponseEntity<Object> handlerBookNotFoundException(BookNotFoundException bookException){
		
		CustomeException excptionObj = new CustomeException(
						bookException.getMessage(),
						bookException.getCause(),
						HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Object>(excptionObj,HttpStatus.NOT_FOUND);
	}
	
}
