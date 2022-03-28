package com.example.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRequestModel {

	@NotBlank(message = "First Name should not be blank")
	private String firstName;
	
	@NotNull(message = "Last Name should not be blank")
	private String lastName;
	
	@Email(message = "Please provide valid email address")
	private String emailId;
	
	private String addressLine;
	
	private String city;
	
	private String state;
	
	@Pattern(regexp = "^\\d{10}$", message = "Please provide valid phone number")
	private String mobileNumber;
	
	@Pattern(regexp = "^\\d{6}$", message = "Please provide valid pin code number")
	private String pinCode;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	
	
}
