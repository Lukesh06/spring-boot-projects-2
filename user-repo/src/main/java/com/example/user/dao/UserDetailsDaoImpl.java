package com.example.user.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.user.entity.UserDetailsEntity;
import com.example.user.model.UserDetailsModel;
import com.example.user.repository.UserDetailsRepository;

@Component
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetailsModel saveUserDetails(UserDetailsModel userDetailsModel) {
		UserDetailsEntity userDetailsEntity = new UserDetailsEntity();

		BeanUtils.copyProperties(userDetailsModel, userDetailsEntity);

		userDetailsEntity = userDetailsRepository.save(userDetailsEntity);

		BeanUtils.copyProperties(userDetailsEntity, userDetailsModel);

		return userDetailsModel;
	}

}
