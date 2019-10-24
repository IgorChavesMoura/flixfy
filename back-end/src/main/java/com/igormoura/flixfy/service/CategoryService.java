package com.igormoura.flixfy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igormoura.flixfy.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		
		return categoryRepository.findAll();
		
	}
	
	public Optional<Category> findOne(Long id){
		
		return Optional.ofNullable(categoryRepository.findOne(id));
		
	}
	
	public Category save(Category c) {
		
		if(c.getId() != null) {
			
			Category categoryDB = categoryRepository.findOne(c.getId());
			
			categoryDB.setDescription(c.getDescription());
			
			categoryDB = categoryRepository.save(categoryDB);
			
			return categoryDB;
			
		} else {
			
			Category categoryDB = categoryRepository.save(c);
			
			return categoryDB;
			
		}
		
	}
	
	public void delete(Long id) {
		
		categoryRepository.delete(id);
		
	}
	
}
