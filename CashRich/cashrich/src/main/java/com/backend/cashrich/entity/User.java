package com.backend.cashrich.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=4, max=15)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String userName;
	
	@NotBlank
	@Size(min=8, max=15)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&#])[A-Za-z\\\\d@$!%*?&#]{8,}$")
	private String Password;
	
	@NotBlank
	@Email
	private String email;
	
	private String firstName;
	private String lastName;
	private String mobile;
	
	public User(@NotBlank @Size(min = 4, max = 15) @Pattern(regexp = "^[a-zA-Z0-9]*$") String userName,
			@NotBlank @Size(min = 8, max = 15) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&#])[A-Za-z\\\\d@$!%*?&#]{8,}$") String password,
			@NotBlank @Email String email, String firstName, String lastName, String mobile) {
		super();
		this.userName = userName;
		Password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
}
