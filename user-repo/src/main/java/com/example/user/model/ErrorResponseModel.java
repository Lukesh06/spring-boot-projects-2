package com.example.user.model;

import java.util.Date;

public class ErrorResponseModel {

	private Date timestamp;
	
	private String message;
	
	private String errorCode;

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorResponseModel(Date timestamp, String message, String errorCode) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errorCode = errorCode;
	}
	
	
}
