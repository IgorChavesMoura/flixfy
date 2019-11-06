package com.igormoura.flixfy.service;

import com.igormoura.flixfy.model.video.Category;
import com.igormoura.flixfy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		
		return categoryRepository.findAll();
		
	}
	
	public Optional<Category> findOne(Long id){
		
		return Optional.ofNullable(categoryRepository.getOne(id));
		
	}
	
	public Category save(Category c) {
		
		if(c.getId() != null) {
			
			Category categoryDB = categoryRepository.getOne(c.getId());
			
			categoryDB.setDescription(c.getDescription());
			
			categoryDB = categoryRepository.save(categoryDB);
			
			return categoryDB;
			
		} else {
			
			Category categoryDB = categoryRepository.save(c);
			
			return categoryDB;
			
		}
		
	}
	
	public void delete(Long id) {
		
		categoryRepository.deleteById(id);
		
	}
	
}
