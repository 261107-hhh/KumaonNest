package com.example.register.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		// Log the exception
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handelResourseNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotValidException.class)
	public ResponseEntity<String> handelResourceNotValidException(ResourceNotValidException ex) {
		return new ResponseEntity<String>(ex.getMessage(),  HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(BadUserLoginDetailsException.class)
	public ResponseEntity<String> handelBadUserLoginDetailException(BadUserLoginDetailsException ex) {
		return new ResponseEntity<String>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadUserDetailsException.class)
	public ResponseEntity<String> handelBadUserDetailException(BadUserDetailsException ex) {
		return new ResponseEntity<String>(ex.getMessage(),  HttpStatus.NOT_FOUND);
	}
}
