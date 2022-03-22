package com.example.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.ErrorResponseModel;
import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@Valid @RequestBody UserRequestModel userRequestModel) {
		
		//
		String test = null;
		int length = test.length();
		
		
		UserResponseModel userResponseModel = populateResponse(userRequestModel);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/multiple", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<UserResponseModel> createMultipleUser(@RequestBody List<UserRequestModel> listUserRequestModel) {

		List<UserResponseModel> listUserResponseModel = new ArrayList<>();
		for (UserRequestModel userRequestModel : listUserRequestModel) {
			UserResponseModel userResponseModel = populateResponse(userRequestModel);
			listUserResponseModel.add(userResponseModel);
		}
		return listUserResponseModel;
	}

	private UserResponseModel populateResponse(UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userRequestModel, userResponseModel);
		String empId = UUID.randomUUID().toString();
		userResponseModel.setEmpId(empId);
		return userResponseModel;
	}

	/*@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		for(ObjectError errorObject:ex.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) errorObject).getField();
			String errorMessage = errorObject.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}
		
		return errors;
	}
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
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException ex) {
		return ex.getMessage();
	}

}
