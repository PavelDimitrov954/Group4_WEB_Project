package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.AdminPhoneNumberRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public static final String INVALID_AUTHORIZATION = "Invalid authorization";
    public static final String YOU_CANNOT_CHANGE_YOUR_USERNAME = "You cannot change your username";
    private final UserRepository userRepository;

    private final AdminPhoneNumberRepository adminPhoneNumberRepository;

    public UserServiceImpl(UserRepository userRepository, AdminPhoneNumberRepository adminPhoneNumberRepository) {
        this.userRepository = userRepository;
        this.adminPhoneNumberRepository = adminPhoneNumberRepository;
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
            throw new AuthorizationException(YOU_CANNOT_CHANGE_YOUR_USERNAME);
        }

        userRepository.update(upddateUser);
    }

    @Override
    public void makeUserAdmin(User adminUser, int userId, String phoneNumber) {
        // Check if the adminUser has the necessary permissions to make a user an admin
        if (!adminUser.isAdmin()) {
            throw new AuthorizationException("You don't have permission to make users admin.");
        }

        // Retrieve the user to be made an admin
        User userToPromote = userRepository.get(userId);

        if (userToPromote != null) {
            // Check if the user is not already an admin
            if (!userToPromote.isAdmin()) {
                // Set the user as an admin
                userToPromote.setAdmin(true);

                // If a phoneNumber is provided, associate it with the user in the admin_phone_number table
                if (phoneNumber != null) {
                    adminPhoneNumberRepository.createAdminPhoneNumber(userToPromote, phoneNumber);
                }

                // Update the user in the database
                userRepository.update(userToPromote);
            } else {
                throw new EntityDuplicateException(userToPromote.getUsername());
            }
        } else {
            throw new EntityNotFoundException("User", userId);
        }
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
