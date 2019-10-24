package com.igormoura.flixfy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igormoura.flixfy.repository.FormatRepository;

@Service
public class FormatService {

	@Autowired
	private FormatRepository formatRepository;
	
	public List<Format> findAll(){
		
		return formatRepository.findAll();
		
	}
	
	public Optional<Format> findOne(Long id){
		
		return Optional.ofNullable(formatRepository.findOne(id));
		
	}
	
	public Format save(Format f) {
		
		if(f.getId() != null) {
			
			Format formatDB = formatRepository.findOne(f.getId());
			
			formatDB.setDescription(f.getDescription());
			
			formatDB = formatRepository.save(formatDB);
			
			return formatDB;
			
		} else {
			
			Format formatDB = formatRepository.save(f);
			
			return formatDB;
			
		}
		
	}
	
	public void delete(Long id) {
		
		formatRepository.delete(id);
		
	}
	
}
