package com.example.user.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.ErrorResponseModel;
import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;
import com.example.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService modifiedUserServiceImpl;

	@Autowired
	UserService userServiceImpl;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = modifiedUserServiceImpl.saveUserDetails(userRequestModel);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/multiple", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<UserResponseModel> createMultipleUser(@RequestBody List<UserRequestModel> listUserRequestModel) {

		List<UserResponseModel> listUserResponseModel = modifiedUserServiceImpl.saveUserDetails(listUserRequestModel);

		return listUserResponseModel;
	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * 
	 * for(ObjectError errorObject:ex.getBindingResult().getAllErrors()) { String
	 * fieldName = ((FieldError) errorObject).getField(); String errorMessage =
	 * errorObject.getDefaultMessage(); errors.put(fieldName, errorMessage); }
	 * 
	 * return errors; }
	 */

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseModel handleValidationExceptions(MethodArgumentNotValidException ex) {
		ErrorResponseModel errorResponseModel = null;

		for (ObjectError errorObject : ex.getBindingResult().getAllErrors()) {

			errorResponseModel = new ErrorResponseModel(new Date(), errorObject.getDefaultMessage(), "002");
		}

		return errorResponseModel;
	}

	@GetMapping
	public List<UserResponseModel> getAllUsers() {
		return modifiedUserServiceImpl.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable Integer userId) {
		UserResponseModel userResponseModel = modifiedUserServiceImpl.getUserByUserId(userId);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@GetMapping("/firstName/{firstName}")
	public List<UserResponseModel> getUserByFirstName(@PathVariable String firstName) {
		return modifiedUserServiceImpl.getUserByFirstName(firstName);

	}

	@GetMapping("/firstNameAndCity")
	public List<UserResponseModel> getUserByFirstNameAndCity(@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String city) {
		return modifiedUserServiceImpl.getUserByFirstNameAndCity(firstName, city);

	}
	
	@GetMapping("/firstNameAndLastName")
	public List<UserResponseModel> getUserByFirstNameAndCity(@RequestParam(required = true) String firstName,
			@RequestParam(required = true) String lastName, @RequestParam(required = true) String operation) {
		return modifiedUserServiceImpl.findByFirstNameLastName(firstName, lastName, operation);

	}

	@PutMapping(value= "/{userId}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> updateUser(@RequestBody UserRequestModel userRequestModel, @PathVariable Integer userId) {
		modifiedUserServiceImpl.updateMobileNumberForUserId(userId, userRequestModel.getMobileNumber());
		ResponseEntity<String> response = new ResponseEntity<>("User Updated Successfully", HttpStatus.CREATED);
		return response;
	} 
}
