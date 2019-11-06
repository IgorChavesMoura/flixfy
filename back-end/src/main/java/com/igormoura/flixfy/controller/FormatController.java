package com.igormoura.flixfy.controller;

import com.igormoura.flixfy.model.video.Format;
import com.igormoura.flixfy.service.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FormatController {

    @Autowired
    private FormatService formatService;



    @GetMapping("/format")
    public ResponseEntity<?> getAllFormats(){

        List<Format> formats = formatService.findAll();

        return ResponseEntity.ok(formats);

    }

    @GetMapping("/format/{id}")
    public ResponseEntity<?> getFormat(@PathVariable("id") Long id){

        Optional<Format> format = formatService.findOne(id);

        if(!format.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(format.get());

    }

}
