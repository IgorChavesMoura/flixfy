package com.igormoura.flixfy.controller;

import com.igormoura.flixfy.model.video.Category;
import com.igormoura.flixfy.model.video.Format;
import com.igormoura.flixfy.service.CategoryService;
import com.igormoura.flixfy.service.FormatService;
import com.igormoura.flixfy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FormatService formatService;


    @PostMapping("/admin/category")
    public ResponseEntity<?> saveCategory(@RequestBody Category c){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //System.out.println(authentication.getName());
        //System.out.println(authentication.getAuthorities());




        categoryService.save(c);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/admin/format")
    public ResponseEntity<?> saveFormat(@RequestBody Format f){

        formatService.save(f);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long categoryId){

        try {

            categoryService.delete(categoryId);

            return ResponseEntity.ok().build();


        } catch(Exception e){

            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/admin/format/{id}")
    public ResponseEntity<?> deleteFormat(@PathVariable("id") Long formatId){

        try {

            formatService.delete(formatId);

            return ResponseEntity.ok().build();

        } catch (Exception e){

            return ResponseEntity.badRequest().build();

        }



    }

    @DeleteMapping("/admin/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId){

        try {

            userService.delete(userId);

            return ResponseEntity.ok().build();


        } catch (Exception e){

            return ResponseEntity.badRequest().build();

        }


    }


}
