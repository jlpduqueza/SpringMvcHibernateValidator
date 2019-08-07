package com.example.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class Login {
	
	@Size(min=5, max=10, message="Your name should be between 5 - 10 characters.")
	private String username;

	@NotNull(message="Please select a password")
	@Length(min=5, max=10, message="Password should be between 5 - 10 charactes")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}