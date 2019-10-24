package com.igormoura.flixfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igormoura.flixfy.model.video.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
	
}
