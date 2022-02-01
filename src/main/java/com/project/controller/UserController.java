package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.User;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		
		return this.userService.getAllUsers();
		
	}
       
	@GetMapping("/{userId}") 
	public User getUser(@PathVariable("userId") int id) {
		
		 Optional<User> user = this.userRepository.findById(id);
		 return user.get();
	}
}
