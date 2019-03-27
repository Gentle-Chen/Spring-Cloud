package com.oocl.spring.cloud.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oocl.spring.cloud.provider.constant.GlobalConstant;
import com.oocl.spring.cloud.provider.model.Response;
import com.oocl.spring.cloud.provider.model.User;
import com.oocl.spring.cloud.provider.service.UserService;


@RestController("/provider2")
public class UserController {
	
	@Autowired
	private UserService userService;
	@PostMapping("/user")
	public Response insertUser(@RequestBody User user){
		Response response = new Response();
		User result = userService.insertUser(user);
		response.setStatus(GlobalConstant.SUCCESS);
		response.setResult(result);
		response.setErrorMsg("this is from provider2");
		return response; 
	}
	
	@GetMapping("/user/{name}")
	public Response getUserByName(@PathVariable("name") String name) {
		Response response = new Response();
		try{
			User user = userService.getUserByName(name);
			response.setStatus(GlobalConstant.SUCCESS);
			response.setResult(user);
			response.setErrorMsg("this is from provider2");
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus("fail");
			response.setErrorMsg(e.getMessage() + " for select");
		}
		return response;
	}
	
	@GetMapping("/users")
	public Response getAllUser() {
		Response response = new Response();
		try{
			List<User> users = userService.getAllUser();
			response.setStatus(GlobalConstant.SUCCESS);
			response.setResult(users);
			response.setErrorMsg("this is from provider2");
		}catch (Exception e) {
			// TODO: handle exception
			response.setStatus("fail");
			response.setErrorMsg(e.getMessage() + " for select");
		}
		return response;
	}
	
	@PutMapping("/user/{name}")
	public Response updateUserByName(@PathVariable("name") String name, @RequestBody User user) {
		Response response = new Response();
		try {
			int i = userService.updateUserByName(name, user);
			response.setStatus(GlobalConstant.SUCCESS);
			response.setUpdateCount(i);
			response.setErrorMsg("this is from provider2");
		}catch (Exception e) {
			response.setStatus("fail");
			response.setErrorMsg(e.getMessage() + " for update");
		}
		return response;
	}
	
	@DeleteMapping("/user/{name}")
	public Response deleteUserByName(@PathVariable("name") String name) {
		Response response = new Response();
		try {
			int i = userService.deleteUserByName(name);
			response.setStatus(GlobalConstant.SUCCESS);
			response.setUpdateCount(i);
			response.setErrorMsg("this is from provider2");
		}catch (Exception e) {
			response.setStatus("fail");
			response.setErrorMsg(e.getMessage() + " for delete");
		}
		return response;
	}
	
}