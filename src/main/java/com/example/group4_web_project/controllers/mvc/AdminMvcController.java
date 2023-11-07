package com.example.group4_web_project.controllers.mvc;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.models.*;
import com.example.group4_web_project.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminMvcController {

    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;


    public AdminMvcController(UserService userService, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }



    @GetMapping("")
    public String showUser(@ModelAttribute("filterOptions") FilterDtoUser filterDtoUser, HttpSession session,Model model) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            System.out.println("USER");
            System.out.println(user.isAdmin());
            if (user.isAdmin()) {
                FilterOptionsUser filterOptionsUser = new FilterOptionsUser(
                        filterDtoUser.getUsername(),
                        filterDtoUser.getEmail(),
                        filterDtoUser.getFirstName());
                System.out.println("FILTER");

                List<User> users = userService.get(filterOptionsUser);
                System.out.println("USER SERVICE");
                model.addAttribute("filterOptions", filterDtoUser);
                model.addAttribute("users", users);
                return "AdminPortalView";

            }
            return "AccessDeniedView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }


    }
}
