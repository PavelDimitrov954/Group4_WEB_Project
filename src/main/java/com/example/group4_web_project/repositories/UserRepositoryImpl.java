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
    public User get(int id) {
        return null;
    }

    @Override
    public User get(String username) {
        return null;
    }

    @Override
    public int getUserCount() {
        return 0;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void register(User user, String phoneNumber) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void makeUserAdmin(User user, String phoneNumber) {

    }
}
