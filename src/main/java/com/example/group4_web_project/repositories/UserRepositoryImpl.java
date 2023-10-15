package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> get() {
        return null;
    }

    @Override
    public List<User> get(int id) {
        return null;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void register(User user, String phoneNumber) {

    }
}
