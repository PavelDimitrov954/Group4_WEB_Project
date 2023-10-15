package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();
    List<User> get(int id);

    void register(User user);
    void register(User user, String phoneNumber );
}
