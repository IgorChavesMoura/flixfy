package com.igormoura.flixfy.model.video;

public enum ContentType {
	
	MOVIE(0),
	TV_SERIES(1);
	
	private final int value;
	
	ContentType(int value){
		
		this.value = value;
		
	}
	
}
