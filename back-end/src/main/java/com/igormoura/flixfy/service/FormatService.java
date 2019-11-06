package com.igormoura.flixfy.service;

import com.igormoura.flixfy.model.video.Format;
import com.igormoura.flixfy.repository.FormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormatService {

	@Autowired
	private FormatRepository formatRepository;
	
	public List<Format> findAll(){
		
		return formatRepository.findAll();
		
	}
	
	public Optional<Format> findOne(Long id){
		
		return Optional.ofNullable(formatRepository.getOne(id));
		
	}
	
	public Format save(Format f) {
		
		if(f.getId() != null) {
			
			Format formatDB = formatRepository.getOne(f.getId());
			
			formatDB.setDescription(f.getDescription());
			
			formatDB = formatRepository.save(formatDB);
			
			return formatDB;
			
		} else {
			
			Format formatDB = formatRepository.save(f);
			
			return formatDB;
			
		}
		
	}
	
	public void delete(Long id) {
		
		formatRepository.deleteById(id);
		
	}
	
}
