package com.igormoura.flixfy.controller;

import com.igormoura.flixfy.dto.LoginDTO;
import com.igormoura.flixfy.dto.TokenDTO;
import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.security.JwtTokenUtil;
import com.igormoura.flixfy.security.JwtUserDetailsService;
import com.igormoura.flixfy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse responseHSR) {

		TokenDTO response = new TokenDTO();

		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		
		SecurityContextHolder.getContext().setAuthentication(auth);

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());

		User user = userService.findByLogin(loginDTO.getUsername()).get();

		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}


		String token = jwtTokenUtil.getToken(userDetails);

		response.setToken(token);
		response.setUser(user);

		return ResponseEntity.ok(response);

	}


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User u){

        userService.save(u);

        return ResponseEntity.ok().build();

    }
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		
		session.invalidate();
		
		
		SecurityContextHolder.clearContext();
		
		return ResponseEntity.ok().build();
	}


	
	
}