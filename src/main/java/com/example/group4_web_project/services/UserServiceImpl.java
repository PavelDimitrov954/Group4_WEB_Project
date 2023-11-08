package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.AdminPhoneNumberRepository;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public static final String INVALID_AUTHORIZATION = "Invalid authorization";
    public static final String YOU_CANNOT_CHANGE_YOUR_USERNAME = "You cannot change your username";
    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    private final  PostRepository postRepository;

    private final AdminPhoneNumberRepository adminPhoneNumberRepository;

    public UserServiceImpl(UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository, AdminPhoneNumberRepository adminPhoneNumberRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.adminPhoneNumberRepository = adminPhoneNumberRepository;
    }




    @Override
    public List<User> get(FilterOptionsUser filterOptionsUser) {



        return userRepository.get(filterOptionsUser);
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

        else{
            userRepository.register(user);
       }
    }

    @Override
    public int getUserCount() {
        return userRepository.getUserCount();
    }

    @Override
    public void update(User user, User upddateUser) {
        if (user.getId() != upddateUser.getId() && !user.isAdmin()) {
            throw new AuthorizationException(INVALID_AUTHORIZATION);
        } else if (!user.getUsername().equals(upddateUser.getUsername())) {
            throw new IllegalArgumentException(YOU_CANNOT_CHANGE_YOUR_USERNAME);
        }

        userRepository.update(upddateUser);
    }
    
    @Override
    public void makeUserAdmin(User adminUser, int userId, String phoneNumber) {
        if (!adminUser.isAdmin()) {
            throw new AuthorizationException("You don't have permission to make users admin.");
        }

        User userToPromote = userRepository.get(userId);

        if (userToPromote.isAdmin()) {
            throw new EntityDuplicateException("User is already an admin: " + userToPromote.getUsername());
        }

        userToPromote.setAdmin(true);

        if (phoneNumber != null) {
            adminPhoneNumberRepository.createAdminPhoneNumber(userToPromote, phoneNumber);
        }

        userRepository.update(userToPromote);
    }


    @Override
    public void delete(int id, User user) {

        User deleteUsed = get(id);
        if (deleteUsed.getId() != user.getId() && !user.isAdmin()) {
            throw new AuthorizationException(INVALID_AUTHORIZATION);
        }


        userRepository.delete(deleteUsed);



    }

    public void blockUser(User user) {
        user.setBlocked(true);
        userRepository.update(user);
    }

    public void unblockUser(User user) {
        user.setBlocked(false);
        userRepository.update(user);
    }
}
