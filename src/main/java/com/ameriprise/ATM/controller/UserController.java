package com.ameriprise.ATM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ameriprise.ATM.models.User;
import com.ameriprise.ATM.services.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;

	// retrieves all users in database
	@GetMapping(value = "/users")
	public List<User> getUsers() {

		return this.userService.getUsers();
	}

	// retreive a single user by accountId
	@GetMapping(value = "/users/{userId}")
	public User getUserById(@PathVariable Long userId) {

		return this.userService.getUserById(userId);
	}
	
	@PostMapping(value = "/users")
	public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
		try {
			this.userService.addUser(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/users")
	public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
		try {
			this.userService.addUser(user);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
