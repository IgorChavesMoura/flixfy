package com.igormoura.flixfy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igormoura.flixfy.repository.VideoContentRepository;

@Service
public class VideoContentService {

	@Autowired
	private VideoContentRepository videoContentRepository;
	
	public List<VideoContent> findAll(){
		
		return videoContentRepository.findAll();
		
	}
	
	public Optional<VideoContent> findOne(Long id){
		
		return Optional.ofNullable(videoContentRepository.findOne(id));
		
	}
	
	public VideoContent save(VideoContent vc) {
		
		if(vc.getId() != null) {
			
			VideoContent vcDB = videoContentRepository.findOne(vc.getId());
			
			vcDB.setTitle(vc.getTitle());
			vcDB.setDuration(vc.getDuration());
			vcDB.setYear(vc.getYear());
			
			vcDB = videoContentRepository.save(vcDB);
			
			return vcDB;
			
		} else {
			
			VideoContent vcDB = videoContentRepository.save(vc);
			
			return vcDB;
			
		}
		
	}
	
	public void delete(Long id) {
		
		videoContentRepository.delete(id);
		
	}
	
}
