package com.oocl.spring.cloud.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oocl.spring.cloud.consumer.model.Response;
import com.oocl.spring.cloud.consumer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	@LoadBalanced
	private RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@PostMapping("/user")
	public Response insertUser(@RequestBody User user){
		HttpEntity<User> httpEntity = new HttpEntity<User>(user);
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider:8090/user", HttpMethod.POST, httpEntity, Response.class);
		return response.getBody();
	}
	
	@GetMapping("/user/{name}")
	public Response getUserByName(@PathVariable("name") String name) {
		Response response = restTemplate.getForObject("http://spring-cloud-provider:8090/user/" + name, Response.class);
		return response; 
	}
	@HystrixCommand(fallbackMethod = "testHystrix")
	@GetMapping("/users")
	public Response getAllUser() {
		Response response = restTemplate.getForObject("http://spring-cloud-provider:8090/users", Response.class);
		return response; 
	}

	public Response testHystrix(){
		logger.info("testHystrix triggered");
		Response response = new Response();
		response.setStatus("success");
		List<User> users = new ArrayList<>();
		User user = new User("Hystrix@oocl.com","Hystrix","female");
		users.add(user);
		response.setResult(users);
		return response;
	}

	@PutMapping("/user/{name}")
	public Response updateUserByName(@PathVariable("name") String name, @RequestBody User user) {
		HttpEntity<User> httpEntity = new HttpEntity<User>(user);
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider:8090/user/" + name, HttpMethod.PUT, httpEntity, Response.class);
		return response.getBody();
	}
	
	@DeleteMapping("/user/{name}")
	public Response deleteUserByName(@PathVariable("name") String name) {
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider:8090/user/" + name, HttpMethod.DELETE, null, Response.class);
		return response.getBody();
	}
	
}