package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.User;

import java.util.List;

public interface UserRepository {



    User get(int id);

    List<User> get(FilterOptionsUser filterOptionsUser);
    User get(String username);

    int getUserCount();

    void register(User user);

    void update(User user);

    void delete(User user);

    void makeUserAdmin(User user, String phoneNumber);
}
