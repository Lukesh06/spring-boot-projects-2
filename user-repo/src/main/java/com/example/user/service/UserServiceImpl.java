package com.example.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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

		UserDetailsModel userDetailsModel = populateUserModelFromRequest(userRequestModel);

		userDetailsModel = UserDetailsDao.saveUserDetails(userDetailsModel);

		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userDetailsModel, userResponseModel);

		return userResponseModel;
	}

	@Override
	public List<UserResponseModel> saveUserDetails(List<UserRequestModel> listUserRequestModel) {

		List<UserDetailsModel> listUserDetailsModel = new ArrayList<>();

		List<UserResponseModel> listUserResponseModel = new ArrayList<>();

		for (UserRequestModel userRequestModel : listUserRequestModel) {
			UserDetailsModel userDetailsModel = populateUserModelFromRequest(userRequestModel);
			listUserDetailsModel.add(userDetailsModel);
		}

		listUserDetailsModel = UserDetailsDao.saveUserDetails(listUserDetailsModel);

		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userDetailsModel, userResponseModel);
			listUserResponseModel.add(userResponseModel);
		}

		return listUserResponseModel;
	}

	public UserDetailsModel populateUserModelFromRequest(UserRequestModel userRequestModel) {
		UserDetailsModel userDetailsModel = new UserDetailsModel();

		BeanUtils.copyProperties(userRequestModel, userDetailsModel);

		String userReferenceNumber = empIdGeneratorUtility.getUserReferenceNumber();
		userDetailsModel.setUserReferenceNumber(userReferenceNumber);

		TimeZone timeZone = TimeZone.getTimeZone("IST");
		final Calendar calendar = Calendar.getInstance(timeZone);
		userDetailsModel.setCreatedDate(calendar.getTime());
		return userDetailsModel;
	}

	@Override
	public List<UserResponseModel> getAllUsers() {

		List<UserResponseModel> listUserResponseModel = new ArrayList<>();

		List<UserDetailsModel> listUserDetailsModel = UserDetailsDao.getAllUsers();

		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userDetailsModel, userResponseModel);
			listUserResponseModel.add(userResponseModel);
		}

		return listUserResponseModel;
	}

	@Override
	public UserResponseModel getUserByUserId(Integer userId) {

		UserDetailsModel userDetailsModel = UserDetailsDao.getUserByUserId(userId);

		UserResponseModel userResponseModel = new UserResponseModel();
		BeanUtils.copyProperties(userDetailsModel, userResponseModel);

		return userResponseModel;
	}

	@Override
	public List<UserResponseModel> getUserByFirstName(String firstName) {

		List<UserResponseModel> listUserResponseModel = new ArrayList<>();

		List<UserDetailsModel> listUserDetailsModel = UserDetailsDao.getUserByFirstName(firstName);

		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userDetailsModel, userResponseModel);
			listUserResponseModel.add(userResponseModel);
		}

		return listUserResponseModel;

	}
	
	@Override
	public List<UserResponseModel> getUserByFirstNameAndCity(String firstName, String city) {

		List<UserResponseModel> listUserResponseModel = new ArrayList<>();

		List<UserDetailsModel> listUserDetailsModel = UserDetailsDao.getUserByFirstNameAndCity(firstName, city);

		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userDetailsModel, userResponseModel);
			listUserResponseModel.add(userResponseModel);
		}

		return listUserResponseModel;

	}

	@Override
	public List<UserResponseModel> findByFirstNameLastName(String firstName, String lastName, String operation) {
		List<UserResponseModel> listUserResponseModel = new ArrayList<>();
		List<UserDetailsModel> listUserDetailsModel = new ArrayList<>();
		if(operation.equalsIgnoreCase("OR")) {
			listUserDetailsModel =  UserDetailsDao.findByFirstNameOrLastName(firstName, lastName);
		}
		else if(operation.equalsIgnoreCase("AND")) {
			listUserDetailsModel =  UserDetailsDao.findByFirstNameAndLastName(firstName, lastName);
		}
		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserResponseModel userResponseModel = new UserResponseModel();
			BeanUtils.copyProperties(userDetailsModel, userResponseModel);
			listUserResponseModel.add(userResponseModel);
		}

		return listUserResponseModel;

	
	}

	@Override
	public void updateMobileNumberForUserId(int userId, String mobileNumber) {
		UserDetailsDao.updateMobileNumberForUserId(userId, mobileNumber);
		
	}
}
