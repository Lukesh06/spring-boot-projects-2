package com.example.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;


@Service("modifiedUserServiceImpl")
public class ModifiedUserServiceImpl implements UserService {

	@Override
	public UserResponseModel populateResponse(UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userRequestModel, userResponseModel);
		String empId = "Custom emp Id";
		userResponseModel.setEmpId(empId);
		return userResponseModel;
	}

}
