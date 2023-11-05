package com.example.group4_web_project.controllers.mvc;


import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.services.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    private final AuthenticationHelper authenticationHelper;
    private final PostService postService;

    @Autowired
    public HomeMvcController(AuthenticationHelper authenticationHelper, PostService postService) {
        this.authenticationHelper = authenticationHelper;
        this.postService = postService;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @GetMapping
    public String showHomePage(Model model) {
        List<com.example.group4_web_project.models.Post> topCommentedPosts = postService.getTopCommentedPosts(10);
        List<com.example.group4_web_project.models.Post> mostRecentPosts = postService.getMostRecentPosts(10);

        model.addAttribute("topCommentedPosts", topCommentedPosts);
        model.addAttribute("mostRecentPosts", mostRecentPosts);
        model.addAttribute("title", "Home");

        return "HomeView";
    }


    @GetMapping("/about")
    public String showAboutPage() {
        return "About";
    }

    @GetMapping("/admin")
    public String showAdminPortal(HttpSession session, Model model) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            if (user.isAdmin()) {
                return "AdminPortalView";
            }
            return "AccessDeniedView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
}

