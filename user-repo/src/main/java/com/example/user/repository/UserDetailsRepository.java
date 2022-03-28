package com.example.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.user.entity.UserDetailsEntity;

public interface UserDetailsRepository extends CrudRepository<UserDetailsEntity, Integer> {

}
