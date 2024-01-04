package com.restServices.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//This is creating a simple restApi service (CRUD) using spring boot framework
@RestController
@RequestMapping("/users")
public class UsersController {
	
	@GetMapping
	public String getUsers(@RequestParam(value="page") int pageNum, @RequestParam(value="page") int limitNum) {
		return "http GET request was sent for page:" +pageNum+"and limit is:"+limitNum;
	}
	
	@GetMapping(path="/{userID}") //Path parameter
	public String getUser(@PathVariable String userID) {
		return "http GET request was sent for userId:"+userID;
	}
	
	@PostMapping
	public String createUser() {
		return "http POST request was sent";
	}
	
	@PutMapping
	public String updateUser() {
		return "http PUT request was sent";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "http DELETE request was sent";
	}

}
