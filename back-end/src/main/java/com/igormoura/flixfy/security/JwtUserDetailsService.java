package com.igormoura.flixfy.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userService.findByLogin(username);
		
		if(user.isPresent()){
			
			return JwtUserFactory.create(user.get());
			
		}
		
//		Optional<User> user = userService.findByEmail(username);
//
//
//		if (user.isPresent()) {
//			return JwtUserFactory.create(user.get());
//		}

		throw new UsernameNotFoundException("Usuário não encontrado. ");

	}

}
