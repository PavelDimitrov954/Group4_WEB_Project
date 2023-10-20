package com.example.group4_web_project.services;

import com.example.group4_web_project.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> get();
    User get(String username);
    User get(int id);
    void register(User user);
    int getUserCount();
    void update(User user, User user1);


}
