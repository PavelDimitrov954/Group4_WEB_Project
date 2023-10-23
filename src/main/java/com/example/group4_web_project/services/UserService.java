package com.example.group4_web_project.services;

import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserService {



    List<User> get(FilterOptionsUser filterOptionsUser);    // Monika
    User get(String username);      // Pavel

    User get(int id);       // Borko

    void register(User user);       // Monika

    int getUserCount();     // Pavel

    void update(User user, User user1);     // Borko

    void makeUserAdmin(User adminUser, int userId, String phoneNumber); // Monika

    void blockUser(User user);      // Pavel
    void delete(int id, User user);     // Borko
    void unblockUser(User user);        // Pavel
}
