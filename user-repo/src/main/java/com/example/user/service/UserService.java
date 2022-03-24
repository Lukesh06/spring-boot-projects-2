package com.example.user.service;

import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

public interface UserService {
	
	 UserResponseModel populateResponse(UserRequestModel userRequestModel);
}
