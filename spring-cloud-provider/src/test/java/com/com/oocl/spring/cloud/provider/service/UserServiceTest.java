package com.com.oocl.spring.cloud.provider.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oocl.spring.cloud.provider.dao.UserDao;
import com.oocl.spring.cloud.provider.model.User;
import com.oocl.spring.cloud.provider.service.impl.UserServiceImpl;


@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

	@Mock
    private UserDao userDao;

    @InjectMocks
    private  UserServiceImpl userService;
	
	@Test
	public void insertUserTest() {
		User user = new User("chenge6@oocl.com", "gentle", "male");
		when(userDao.insertUser(user)).thenReturn(user);
		User result = userService.insertUser(user);
		assertEquals(user, result);
	}
	
	@Test
	public void should_return_all_user_when_select_all_user() {
		List<User> list = new ArrayList<>();
		when(userDao.getAllUser()).thenReturn(list);
		List<User> users = userService.getAllUser();
		assertEquals(0, users.size());
		assertNotNull(users);
	}

	@Test
	public void should_return_user_count_when_input_right_name_for_select() {
		String name = "gentle";
		User u = new User("chenge6@oocl.com", "gentle", "male");
		when(userDao.getUserByName(name)).thenReturn(u);
		User user = userService.getUserByName(name);
		assertEquals(name, user.getName());
	}
	
	@Test
	public void should_return_update_count_when_input_right_name_for_update() {
		String name = "gentle";
		User user = new User("chenge@oocl.com", "gentle", "male");
		when(userDao.updateUserByName(name,user)).thenReturn(1);
		int result = userService.updateUserByName(name, user);
		assertEquals(1, result);
	}
	
	@Test
	public void should_return_user_delete_count_when_input_right_name_for_delete() {
		String name = "gentle";
		when(userDao.deleteUserByName(name)).thenReturn(1);
		int result = userService.deleteUserByName(name);
		assertEquals(1, result);
	}
	
	
	/*
	 *  exception test
	 */
	@Test(expected = NullPointerException.class) 
	public void should_throw_null_exception_when_input_wrong_name_for_select() {
		String name = "666";
		userService.getUserByName(name);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void should_throw_null_exception_when_input_wrong_name_for_update() {
		String name = "666";
		User user = new User("chenge@oocl.com", "gentle", "male");
		userService.updateUserByName(name, user);
	}
	
	@Test(expected = NullPointerException.class)
	public void should_throw_null_exception_when_input_wrong_name_for_delete() {
		String name = "666";
		userService.deleteUserByName(name);
	}
	
}
