package com.torresdevelop.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.torresdevelop.users.models.Users;
import com.torresdevelop.users.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	
	@GetMapping
	// Metodo HTTP + Recurso  - Handler Methods
	public ResponseEntity<List <Users>> getUsersAll(@RequestParam (value = "startWith", required = false)String startWith){
		return new ResponseEntity<List<Users>>(
				userService.getUsers(startWith), HttpStatus.OK);				
				
	}
	 
	@GetMapping(value = "/{username}")
	public ResponseEntity<Users> getUserByUserName(@PathVariable ("username") String username){
		return new ResponseEntity<Users>(userService.getUserByUserName(username), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user){
		return new ResponseEntity<Users>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/{username}")
	public ResponseEntity<Users> updateUser(@PathVariable("username") String username, 
			@RequestBody Users user){
		return new ResponseEntity<Users>(userService.updateUser(user, username), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		userService.deleteUser(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

