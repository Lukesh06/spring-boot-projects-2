package com.example.user.dao;

import java.util.List;

import com.example.user.model.UserDetailsModel;

public interface UserDetailsDao {

	UserDetailsModel saveUserDetails(UserDetailsModel userDetailsModel);
	
	List<UserDetailsModel> saveUserDetails(List<UserDetailsModel> listUserDetailsModel);
	
	List<UserDetailsModel> getAllUsers();
	
	UserDetailsModel getUserByUserId(Integer userId);
	
	List<UserDetailsModel> getUserByFirstName(String firstName);
	
}
