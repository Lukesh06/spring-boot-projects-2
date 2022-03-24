package com.example.user.service;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.Utility.EmpIdGeneratorUtility;
import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	@Autowired
	EmpIdGeneratorUtility empIdGeneratorUtility;

	@Override
	public UserResponseModel populateResponse(UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userRequestModel, userResponseModel);
		String empId = empIdGeneratorUtility.getEmpId();
		userResponseModel.setEmpId(empId);
		return userResponseModel;
	}

}
