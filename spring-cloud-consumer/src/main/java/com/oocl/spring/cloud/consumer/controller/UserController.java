package com.oocl.spring.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oocl.spring.cloud.consumer.model.Response;
import com.oocl.spring.cloud.consumer.model.User;

@RestController
public class UserController {
	
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
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider/user", HttpMethod.POST, httpEntity, Response.class);
		return response.getBody();
	}
	
	@GetMapping("/user/{name}")
	public Response getUserByName(@PathVariable("name") String name) {
		Response response = restTemplate.getForObject("http://spring-cloud-provider/user/" + name, Response.class);
		return response; 
	}
	
	@GetMapping("/users")
	public Response getAllUser() {
		Response response = restTemplate.getForObject("http://spring-cloud-provider/users", Response.class);
		return response; 
	}
	
	@PutMapping("/user/{name}")
	public Response updateUserByName(@PathVariable("name") String name, @RequestBody User user) {
		HttpEntity<User> httpEntity = new HttpEntity<User>(user);
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider/user/" + name, HttpMethod.PUT, httpEntity, Response.class);
		return response.getBody();
	}
	
	@DeleteMapping("/user/{name}")
	public Response deleteUserByName(@PathVariable("name") String name) {
		ResponseEntity<Response> response = restTemplate.exchange("http://spring-cloud-provider/user/" + name, HttpMethod.DELETE, null, Response.class);
		return response.getBody();
	}
	
}