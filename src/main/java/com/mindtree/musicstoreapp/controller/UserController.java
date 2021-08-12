package com.mindtree.musicstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.musicstoreapp.dto.UserDto;
import com.mindtree.musicstoreapp.entity.Role;
import com.mindtree.musicstoreapp.entity.User;
import com.mindtree.musicstoreapp.exception.MainException;
import com.mindtree.musicstoreapp.exception.service.ServiceException;
import com.mindtree.musicstoreapp.service.IUserService;
import com.mindtree.musicstoreapp.serviceimpl.UserDetailsServiceImpl;
import com.mindtree.musicstoreapp.serviceimpl.UserServiceImpl;

@RestController()
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService=new UserServiceImpl();
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody User user) throws MainException{
		Role role=new Role("normal");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return new ResponseEntity<String>(userService.register(user,role),HttpStatus.ACCEPTED );
	}
	@PutMapping("/login")
	public ResponseEntity<?>login(@RequestBody UserDto user) throws MainException{
		
		return new ResponseEntity<String>(userService.login(user),HttpStatus.ACCEPTED );
	}
	@GetMapping("/")
	public String getHomepage() throws MainException{
		
		return "<h1>HI</h1>";
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam String username) throws MainException{
		
		return new ResponseEntity<>(userDetailsServiceImpl.loadUserByUsername(username),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/give-access")
	public ResponseEntity<?>giveAccess(@RequestParam String username) throws MainException{
		System.out.println("Username "+username);
		return new ResponseEntity<>(userService.giveAccess(username),HttpStatus.ACCEPTED);
		
	}
	
	
	
}
