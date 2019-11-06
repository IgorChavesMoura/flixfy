package com.igormoura.flixfy.service;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.model.user.UserProfile;
import com.igormoura.flixfy.model.video.VideoContent;
import com.igormoura.flixfy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VideoContentService videoContentService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	public Optional<User> findByLogin(String login){
		
		return Optional.ofNullable(userRepository.findByLogin(login));
		
	}

	public List<User> findByProfile(UserProfile profile){

		return userRepository.findByProfile(profile);

	}
	public List<User> findByNameOrLogin(String query){

		return userRepository.findByLoginContainingOrNameContaining(query,query);

	}

	public List<User> findAll(){
		
		return userRepository.findAll(); 
		
	}
	
	public Optional<User> findOne(Long id){
		
		return Optional.ofNullable(userRepository.getOne(id));
		
	}



	public User save(User u) {

		u.setProfile(UserProfile.USER);
		
		if(u.getId() != null) {
			
			User userDB = userRepository.getOne(u.getId());
			
			userDB.setEmail(u.getEmail());
			userDB.setName(u.getName());

			if(u.getPassword() != null){

                userDB.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));

            }
			
			userDB = userRepository.save(userDB);
			
			return userDB;
			
		} else {

			u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));

			User userDB = userRepository.save(u);
			
			return userDB;
		
		}
		
	}
	
	public void delete(Long id) {

		User user = findOne(id).get();

		for(VideoContent vc : user.getVideos()){

			videoContentService.delete(vc.getId());


		}

		List<VideoContent> orphanVcs = new ArrayList<>();

		orphanVcs.addAll(user.getVideos());

		user.setVideos(new ArrayList<>());

		user = userRepository.save(user);
		
		userRepository.delete(user);

		for(VideoContent orphanVc : orphanVcs){

		    videoContentService.delete(orphanVc.getId());

        }
		
	}
}
