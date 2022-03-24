package com.example.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return "From Globle Exception handler "+ex.getMessage();
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		String message = "From Globle exception handler "+ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return  response;
	}
	
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex) {
		String message = "From Globle exception handler "+ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		return  response;
	}
	
	
}
