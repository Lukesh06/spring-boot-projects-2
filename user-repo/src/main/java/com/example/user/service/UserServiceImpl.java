package com.example.user.service;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.Utility.EmpIdGeneratorUtility;
import com.example.user.dao.UserDetailsDao;
import com.example.user.model.UserDetailsModel;
import com.example.user.model.UserRequestModel;
import com.example.user.model.UserResponseModel;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	EmpIdGeneratorUtility empIdGeneratorUtility;

	@Autowired
	UserDetailsDao UserDetailsDao;

	@Override
	public UserResponseModel populateResponse(UserRequestModel userRequestModel) {
		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userRequestModel, userResponseModel);
		String userReferenceNumber = empIdGeneratorUtility.getUserReferenceNumber();
		userResponseModel.setUserReferenceNumber(userReferenceNumber);
		return userResponseModel;
	}

	@Override
	public UserResponseModel saveUserDetails(UserRequestModel userRequestModel) {

		UserDetailsModel userDetailsModel = new UserDetailsModel();

		BeanUtils.copyProperties(userRequestModel, userDetailsModel);

		String userReferenceNumber = empIdGeneratorUtility.getUserReferenceNumber();
		userDetailsModel.setUserReferenceNumber(userReferenceNumber);
		
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		final Calendar calendar = Calendar.getInstance(timeZone);
		userDetailsModel.setCreatedDate(calendar.getTime());

		userDetailsModel = UserDetailsDao.saveUserDetails(userDetailsModel);

		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userDetailsModel, userResponseModel);

		return userResponseModel;
	}

}
