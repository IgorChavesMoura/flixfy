package com.igormoura.flixfy.controller;

import com.igormoura.flixfy.model.video.Category;
import com.igormoura.flixfy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> getCategories(){

        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id){

        Optional<Category> category = categoryService.findOne(id);

        if(!category.isPresent()){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(category.get());

    }

}
