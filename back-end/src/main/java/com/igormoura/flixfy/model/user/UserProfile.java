package com.igormoura.flixfy.model.user;

public enum UserProfile {
	
	USER(0),
	ADMIN(1);
	
	
	private final int value;
	
	UserProfile(int value){
		
		this.value = value;
		
	}
	
}
