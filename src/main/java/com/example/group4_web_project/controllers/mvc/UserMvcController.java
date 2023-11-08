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

    @ModelAttribute("isAdmin")
    public boolean populateIsAdmin(HttpSession session){
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            if(user.isAdmin()){
                return true;
            }
            return false;
        }catch (AuthorizationException e){
            return false;
        }
    }
    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }


    @GetMapping("/current")
    public String showUserInfo(Model model, HttpSession session) {
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
    @GetMapping("/current/{id}")
    public String showUserInfoAdmin(Model model,@PathVariable int id,HttpSession session) {
        try{
            authenticationHelper.tryGetCurrentUser(session);
            User user = userService.get(id);
            List<Post> userPosts = postService.getByCreator(user);
            model.addAttribute("user", user);
            model.addAttribute("posts", userPosts);
            return "UserAdminView";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }



    }


    @GetMapping("/current/{id}/blocked")
    public String blocked( Model model,@PathVariable int id, HttpSession session) {
        try{

          User user = userService.get(id);



            userService.blockUser(user);


            return "redirect:/users/current/{id}";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

        @GetMapping("/current/{id}/unblocked")
        public String Unblocked( Model model,@PathVariable int id, HttpSession session) {
            try{

                User user = userService.get(id);



                userService.unblockUser(user);


                return "redirect:/users/current/{id}";
            } catch (AuthorizationException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
            }



    }
    @GetMapping("/current/{id}/makeAdmin")
    public String makeUserAdmin( Model model, @PathVariable int id, HttpSession session) {
        try{
           User admin =   authenticationHelper.tryGetCurrentUser(session);
           System.out.println("Hello" + id);



            userService.makeUserAdmin(admin,id,null);
            return "redirect:/users/current/{id}";

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }



    }
}
