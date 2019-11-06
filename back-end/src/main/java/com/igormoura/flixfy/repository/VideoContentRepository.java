package com.igormoura.flixfy.repository;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.model.video.Category;
import com.igormoura.flixfy.model.video.VideoContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoContentRepository extends JpaRepository<VideoContent, Long>{

	public List<VideoContent> findByOwner(User onwer);

	public List<VideoContent> findByTitleContaining(String title);

	public List<VideoContent> findByCategories(Category category);

	public List<VideoContent> findByTitleContainingAndCategories(String title, Category category);
	
}
