package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserRepository {

    List<User> get();  // Monika
    User get(int id); //Pavel DONE
    User get(String username);  // Borko DONE
    int getUserCount();  // Monika
    void register(User user);  // Pavel     DONE
     // Borko TODO
    void update(User user);  // Monika
    void delete(User user);  // Monika
    void makeUserAdmin(User user, String phoneNumber);  // Pavel
}
