package com.igormoura.flixfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igormoura.flixfy.model.video.VideoContent;

@Repository
public interface VideoContentRepository extends JpaRepository<VideoContent, Long>{

	
	
}
