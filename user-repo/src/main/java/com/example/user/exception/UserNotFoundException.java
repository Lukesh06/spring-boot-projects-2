package com.example.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -205085289146014982L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
