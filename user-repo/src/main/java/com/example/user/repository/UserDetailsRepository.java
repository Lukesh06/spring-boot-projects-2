package com.example.user.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.user.entity.UserDetailsEntity;

public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, Integer> {

	List<UserDetailsEntity> findByFirstNameOrderByCity(String firstName);
	
	List<UserDetailsEntity> findByFirstNameAndCity(String firstName, String city);

	List<UserDetailsEntity> findByFirstNameOrLastName(String firstName, String lastName);
	
	List<UserDetailsEntity> findByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
	
	@Transactional
	@Modifying
	@Query(value="Update USER_DETAILS set MOBILE_NUMBER = :mobileNumber where USER_ID =:userId",nativeQuery = true)
	void updateMobileNumberForUserId(@Param("userId") int userId, @Param("mobileNumber") String mobileNumber);
}
