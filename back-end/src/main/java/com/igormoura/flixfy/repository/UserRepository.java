package com.igormoura.flixfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igormoura.flixfy.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByLogin(String login);
	
}
