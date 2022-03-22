package com.example.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = populateResponse(userRequestModel);
		ResponseEntity<UserResponseModel> response = new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
		return response;
	}

	@PostMapping(value = "/multiple", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE})
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

}
