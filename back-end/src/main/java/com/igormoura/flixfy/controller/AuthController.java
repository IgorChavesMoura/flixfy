package com.igormoura.flixfy.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.igormoura.flixfy.model.user.User;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

@RestController
public class AuthController {
	
	@PostMapping("/login")
	private ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse responseHSR) {

		

		TokenDto response = new TokenDto();

		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

		
		SecurityContextHolder.getContext().setAuthentication(auth);

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());

		User user = userService.findByEmail(loginDTO.getUsername()).get();

		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		 	
		if(userService.isUserBlocked(user.getUuid())) {
			
			try {
				responseHSR.sendError(HttpServletResponse.SC_UNAUTHORIZED, "user.blocked");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return null;
		}

		String token = jwtTokenUtil.getToken(userDetails);

		response.setToken(token);
		response.setUser(user);

		return ResponseEntity.ok(response);

	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		
		session.invalidate();
		
		
		SecurityContextHolder.clearContext();
		
		return ResponseEntity.ok().build();
	}
	
	
}