package com.example.user.exception;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.user.model.ErrorResponseModel;

@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return "From Globle Exception handler " + ex.getMessage();
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		String message = "From Globle exception handler " + ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<String> handleInvalidRequestException(InvalidRequestException ex) {
		String message = "From Globle exception handler " + ex.getMessage();
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseModel> handleUserNotFoundException(UserNotFoundException ex) {

		TimeZone timeZone = TimeZone.getTimeZone("IST");
		final Calendar calendar = Calendar.getInstance(timeZone);

		ErrorResponseModel errorResponseModel = new ErrorResponseModel(calendar.getTime(), ex.getMessage(), "DB001");
		ResponseEntity<ErrorResponseModel> response = new ResponseEntity<ErrorResponseModel>(errorResponseModel,
				HttpStatus.BAD_REQUEST);

		return response;
	}

}
