package com.LightHouseLibrary.BookService.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomeException {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;

}
