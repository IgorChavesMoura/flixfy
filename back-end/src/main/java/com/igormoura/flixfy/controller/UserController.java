package com.igormoura.flixfy.controller;

import com.igormoura.flixfy.dto.UserExistsDTO;
import com.igormoura.flixfy.model.user.User;
import com.igormoura.flixfy.model.user.UserProfile;
import com.igormoura.flixfy.service.UserService;
import com.igormoura.flixfy.service.VideoContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoContentService videoContentService;


    @GetMapping("/user/")
    public ResponseEntity<?> getAllUsers(@RequestParam(name = "query", required = false) Optional<String> query){

        if(query.isPresent()){

            List<User> users = userService.findByNameOrLogin(query.get());


            return ResponseEntity.ok(users);
        }

        List<User> users = userService.findByProfile(UserProfile.USER);

        return ResponseEntity.ok(users);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){

        Optional<User> user = userService.findOne(id);

        if(!user.isPresent()){

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(user.get());

    }

    @GetMapping("/user/{id}/vcs")
    public ResponseEntity<?> getUserVideos(@PathVariable("id") Long id){

        User u = userService.findOne(id).get();

        return ResponseEntity.ok(u.getVideos());

    }

    @GetMapping("/user-exists")
    public ResponseEntity<?> userExists(@RequestParam("login") String login){

        Optional<User> user = userService.findByLogin(login);

        UserExistsDTO dto = new UserExistsDTO();

        dto.setExists(user.isPresent());

        return ResponseEntity.ok(dto);

    }

    @PostMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user){

        User userDB = userService.save(user);


        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser(HttpSession session){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userService.findByLogin(currentPrincipalName).get();

        userService.delete(user.getId());

        session.invalidate();


        SecurityContextHolder.clearContext();

        return ResponseEntity.ok().build();

    }

}


