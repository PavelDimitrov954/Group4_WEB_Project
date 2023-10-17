package com.example.group4_web_project.controllers;

import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/users")
public class UserRestController {

    private final UserRepository userRepository;


    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public List<User> get(){
        return userRepository.get();
    }


    @GetMapping("/count")
    public int getUserCount(){
        return userRepository.getUserCount();
    }

    @PutMapping("/{id}")
    public void update (@PathVariable int id, @RequestBody User user){

        userRepository.update(user);

    }
}
