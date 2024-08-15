package com.backend.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.cashrich.entity.User;
import com.backend.cashrich.service.CoinService;
import com.backend.cashrich.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CoinService coinService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody @Valid User user){
		
		User createdUser = userService.signUp(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password){
		
		User loggedInUser = userService.login(username,password);
		return ResponseEntity.ok(loggedInUser);
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateProfile(@RequestParam Long userId, @RequestBody User user){
		User updatedUser = userService.updateProfile(userId, user);
		return ResponseEntity.ok(updatedUser);
	}

	@GetMapping("/coindata")
	public ResponseEntity<String> getCoinData(@RequestParam Long userId, @RequestParam String symbols) {
		
		User user =userService.login(userService.findById(userId).getUsername(), userService.findById(userId).getPassword());
		String reponse = coinService.getCoinData(user, symbols);
		return ResponseEntity.ok(response);
	}
	

}
