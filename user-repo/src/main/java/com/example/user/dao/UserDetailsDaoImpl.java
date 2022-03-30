package com.example.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.user.entity.UserDetailsEntity;
import com.example.user.exception.UserNotFoundException;
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

	@Override
	public List<UserDetailsModel> saveUserDetails(List<UserDetailsModel> listUserDetailsModel) {

		List<UserDetailsEntity> listUserDetailsEntity = new ArrayList<>();

		for (UserDetailsModel userDetailsModel : listUserDetailsModel) {
			UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
			BeanUtils.copyProperties(userDetailsModel, userDetailsEntity);
			listUserDetailsEntity.add(userDetailsEntity);
		}

		listUserDetailsEntity = (List<UserDetailsEntity>) userDetailsRepository.saveAll(listUserDetailsEntity);

		listUserDetailsModel = new ArrayList<>();

		for (UserDetailsEntity userDetailsEntity : listUserDetailsEntity) {
			UserDetailsModel userDetailsModel = new UserDetailsModel();
			BeanUtils.copyProperties(userDetailsEntity, userDetailsModel);
			listUserDetailsModel.add(userDetailsModel);
		}

		return listUserDetailsModel;
	}

	@Override
	public List<UserDetailsModel> getAllUsers() {

		List<UserDetailsModel> listUserDetailsModel = new ArrayList<>();

		List<UserDetailsEntity> listUserDetailsEntity = (List<UserDetailsEntity>) userDetailsRepository.findAll();

		for (UserDetailsEntity userDetailsEntity : listUserDetailsEntity) {
			UserDetailsModel userDetailsModel = new UserDetailsModel();
			BeanUtils.copyProperties(userDetailsEntity, userDetailsModel);
			listUserDetailsModel.add(userDetailsModel);
		}

		return listUserDetailsModel;
	}

	@Override
	public UserDetailsModel getUserByUserId(Integer userId) {

		UserDetailsModel userDetailsModel = new UserDetailsModel();

		Optional<UserDetailsEntity> userDetailsEntityOptional = userDetailsRepository.findById(userId);

		if (userDetailsEntityOptional.isPresent()) {
			UserDetailsEntity userDetailsEntity = userDetailsEntityOptional.get();
			BeanUtils.copyProperties(userDetailsEntity, userDetailsModel);
		} else {
			throw new UserNotFoundException("User Not found for User Id " + userId);
		}

		return userDetailsModel;
	}

	@Override
	public List<UserDetailsModel> getUserByFirstName(String firstName) {

		List<UserDetailsModel> listUserDetailsModel = new ArrayList<>();

		List<UserDetailsEntity> listUserDetailsEntity = (List<UserDetailsEntity>) userDetailsRepository.findByFirstName(firstName);

		for (UserDetailsEntity userDetailsEntity : listUserDetailsEntity) {
			UserDetailsModel userDetailsModel = new UserDetailsModel();
			BeanUtils.copyProperties(userDetailsEntity, userDetailsModel);
			listUserDetailsModel.add(userDetailsModel);
		}

		return listUserDetailsModel;
	}

}
