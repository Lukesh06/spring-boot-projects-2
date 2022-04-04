package com.example.user.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.user.model.UserDetailsModel;

public interface UserDetailsDao {

	UserDetailsModel saveUserDetails(UserDetailsModel userDetailsModel);

	List<UserDetailsModel> saveUserDetails(List<UserDetailsModel> listUserDetailsModel);

	List<UserDetailsModel> getAllUsers();

	UserDetailsModel getUserByUserId(Integer userId);

	List<UserDetailsModel> getUserByFirstName(String firstName);

	List<UserDetailsModel> getUserByFirstNameAndCity(String firstName, String city);

	List<UserDetailsModel> findByFirstNameOrLastName(String firstName, String lastName);

	List<UserDetailsModel> findByFirstNameAndLastName(String firstName, String lastName);
	
	void updateMobileNumberForUserId(int userId, String mobileNumber);
}
