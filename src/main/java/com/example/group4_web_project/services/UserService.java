package com.example.group4_web_project.services;

import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserService {

    List<User> get();

    User get(String username);

    User get(int id);

    void register(User user);

    int getUserCount();

    void update(User user, User user1);

    void makeUserAdmin(User adminUser, int userId, String phoneNumber);

    void blockUser(User user);

    void unblockUser(User user);
}
