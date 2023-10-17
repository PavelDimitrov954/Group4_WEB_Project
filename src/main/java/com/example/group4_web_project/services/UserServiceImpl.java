package com.example.group4_web_project.services;

import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> get() {
        return userRepository.get();
    }

    @Override
    public int getUserCount() {
        return userRepository.getUserCount();
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }
}
