package com.oocl.spring.cloud.provider.service;

import java.util.List;

import com.oocl.spring.cloud.provider.model.User;


public interface UserService {

	User insertUser(User user);

	User getUserByName(String name);
	
	List<User> getAllUser();

	int updateUserByName(String name, User user);

	int deleteUserByName(String name);

}
