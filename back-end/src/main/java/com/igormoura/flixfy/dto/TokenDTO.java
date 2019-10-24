package com.igormoura.flixfy.dto;

import com.igormoura.flixfy.model.user.User;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public class TokenDTO {
	
	private String token;
	private User user;
	
	public TokenDto() {
		
	}
	
	public TokenDto(String token, User user) {
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
