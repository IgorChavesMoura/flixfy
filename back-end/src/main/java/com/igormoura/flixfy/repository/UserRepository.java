package com.igormoura.flixfy.repository;

import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByLogin(String login);

	public List<User>  findByProfile(UserProfile profile);

	public List<User> findByLoginContainingOrNameContaining(String login, String name);
	
}
