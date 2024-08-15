package com.backend.cashrich.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.backend.cashrich.entity.User;
import com.backend.cashrich.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User signUp(User user){
		
		if(userRepository.findByUsername(user.getUserName())!=null){
			throw new RuntimeException("Username alreay exists");
		}
		
		if(userRepository.findByEmail(user.getEmail())!=null) {
			throw new RuntimeException("Email already exists");
		}
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
		
	}
	
	
	public User login(String username,String password){
		
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Invalid username or password"));
		
		if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid username or password");
		}
		
		return user;
		
	}
	
	
	public User updateProfile(Long userId,User updatedUser){
		
		User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
		
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setMobile(updatedUser.getMobile());
		
		if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
			user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
		}
			
		return userRepository.save(user);
	}

}
