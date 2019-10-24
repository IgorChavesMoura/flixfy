package com.igormoura.flixfy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public Optional<User> findByLogin(String login){
		
		return Optional.ofNullable(userRepository.findByLogin(login));
		
	}
	
	public List<User> findAll(){
		
		return userRepository.findAll(); 
		
	}
	
	public Optional<User> findOne(Long id){
		
		return Optional.ofNullable(userRepository.findOne(id));
		
	}
	
	public User save(User u) {
		
		if(u.getId() != null) {
			
			User userDB = userRepository.findOne(u.getId());
			
			userDB.setEmail(u.getEmail());
			userDB.setName(u.getName());
			userDB.setPassword(u.getPassword());
			
			userDB = userRepository.save(userDB);
			
			return userDB;
			
		} else {
			
			User userDB = userRepository.save(u);
			
			return userDB;
		
		}
		
	}
	
	public void delete(Long id) {
		
		userRepository.delete(id);
		
	}
}
