package com.example.group4_web_project.services;

import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserService {

    List<User> get();
    int getUserCount();
    void update(User user);


}
