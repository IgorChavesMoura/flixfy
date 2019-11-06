package com.igormoura.flixfy.dto;

import com.igormoura.flixfy.model.user.User;

public class TokenDTO {
	
	private String token;
	private User user;
	
	public TokenDTO() {
		
	}
	
	public TokenDTO(String token, User user) {
		this.setToken(token);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
