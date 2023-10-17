package com.example.group4_web_project.services;
import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public static final String INVALID_AUTHORIZATION = "Invalid authorization";
    public static final String YOU_CANNOT_CHANGE_YOUR_USERNAME = "You cannot change your username";
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> get() {
        return userRepository.get();
    }

    @Override
    public User get(String username) {
        return userRepository.get(username);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public void register(User user) {
        boolean duplicateExists = true;
        try {
            userRepository.get(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        userRepository.register(user);
    }

    @Override
    public int getUserCount() {
        return userRepository.getUserCount();
    }

    @Override
    public void update(User user1, User user) {
        if(user1.getId() != user.getId() ){
            throw new  AuthorizationException(INVALID_AUTHORIZATION);
        }

        else if(!user1.getUsername().equals(user.getUsername())){
            throw new AuthorizationException(YOU_CANNOT_CHANGE_YOUR_USERNAME);
        }

        userRepository.update(user);
    }
}
