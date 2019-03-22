package com.oocl.spring.cloud.provider.dao;

import java.util.List;

import com.oocl.spring.cloud.provider.model.User;


public interface UserDao {

	User insertUser(User user);

	List<User> getAllUser();

	User getUserByName(String name);

	int updateUserByName(String name, User user);

	int deleteUserByName(String name);

}
