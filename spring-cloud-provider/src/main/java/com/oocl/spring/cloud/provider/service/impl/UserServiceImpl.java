package com.oocl.spring.cloud.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oocl.spring.cloud.provider.dao.UserDao;
import com.oocl.spring.cloud.provider.model.User;
import com.oocl.spring.cloud.provider.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(name);
	}

	@Override
	public int updateUserByName(String name, User user) {
		// TODO Auto-generated method stub
		return userDao.updateUserByName(name, user);
	}

	@Override
	public int deleteUserByName(String name) {
		// TODO Auto-generated method stub
		return userDao.deleteUserByName(name);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

}
