package com.example.group4_web_project.helpers;

import com.example.group4_web_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {
    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String INVALID_AUTHENTICATION_ERROR = "Invalid authentication.";

    private final UserService userService;


    @Autowired
    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }
}
