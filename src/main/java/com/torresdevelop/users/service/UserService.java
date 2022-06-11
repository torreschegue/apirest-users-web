package com.torresdevelop.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.torresdevelop.users.models.Users;

@Service
public class UserService {
	
	@Autowired
	private Faker faker;
	
	/**
	 * Codigo para mi Servicio.
	 * 
	 * **/
	
	private List<Users> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for (int i = 0; i<100; i++) {
			users.add(new Users( faker.idNumber().valid(),  faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
			
		}
		
	}

	public List<Users> getUsers(String startWith) {
		if(startWith != null) {
			return users.stream().filter(u -> u.getUserName()
					.startsWith(startWith))
					.collect(Collectors.toList());
		}else {
			return users;
		}
		
	}
	
	public Users getUserByUserName(String username) {
		return users.stream()
				.filter(u -> u.getUserName().equals(username)).findAny()
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,
						String.format("User %s not found", username)));
	}
	
	public Users createUser(Users user) {
		if(users.stream().anyMatch(u-> u.getUserName().equals(user.getUserName()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					String.format("User %s Already exists", user.getUserName()));
		}
		users.add(user);
		return user;
	}
	
	
	public Users updateUser(Users user, String username) {
		Users userTobeUpdated = getUserByUserName(username);
		userTobeUpdated.setNickName(user.getNickName());
		userTobeUpdated.setPassword(user.getPassword());
		return userTobeUpdated;
	}
	
	
	public void deleteUser(String username) {
		Users getUserByUserName = getUserByUserName(username);
		users.remove(getUserByUserName);
	}
	
	
}
