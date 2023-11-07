package com.example.group4_web_project.controllers.mvc;


import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.services.PostService;
import com.example.group4_web_project.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;
    private final PostService postService;
    private final AuthenticationHelper authenticationHelper;


    @Autowired
    public UserMvcController(UserService userService, PostService postService, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }


    @GetMapping("/current")
    public String showUserInfo(@RequestHeader HttpHeaders headers, Model model, HttpSession session) {
        try{
           User user = authenticationHelper.tryGetCurrentUser(session);
            List<Post> userPosts = postService.getByCreator(user);
            model.addAttribute("user", user);
            model.addAttribute("posts", userPosts);
            return "UserView";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }



    }
}
