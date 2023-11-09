package com.example.group4_web_project.controllers.mvc;


import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.models.*;
import com.example.group4_web_project.repositories.AdminPhoneNumberRepository;
import com.example.group4_web_project.helpers.UserMapper;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.models.UserDto;
import com.example.group4_web_project.services.PostService;
import com.example.group4_web_project.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;
    private final PostService postService;
    private final UserMapper userMapper;
    private final AuthenticationHelper authenticationHelper;
    private final AdminPhoneNumberRepository adminPhoneNumberRepository;


    @Autowired
    public UserMvcController(UserService userService, PostService postService, UserMapper userMapper, AuthenticationHelper authenticationHelper, AdminPhoneNumberRepository adminPhoneNumberRepository) {

        this.userService = userService;
        this.postService = postService;
        this.userMapper = userMapper;
        this.authenticationHelper = authenticationHelper;
        this.adminPhoneNumberRepository = adminPhoneNumberRepository;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("isAdmin")
    public boolean populateIsAdmin(HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            if (user.isAdmin()) {
                return true;
            }
            return false;
        } catch (AuthorizationException e) {
            return false;

        }
    }

    @ModelAttribute("isPhoneNumber")
    public boolean populateIsPhoneNumber(HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            String name = adminPhoneNumberRepository.GetPhoneNumber(user);

            return true;
        } catch (AuthorizationException | EntityNotFoundException e) {

            return false;
        }

    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }


    @GetMapping("/current")
    public String showUserInfo(Model model, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            List<Post> userPosts = postService.getByCreator(user);
            model.addAttribute("user", user);
            model.addAttribute("posts", userPosts);
            model.addAttribute("adminPhoneNumber", new AdminPhoneNumberDto());
            try {
                AdminPhoneNumber adminPhoneNumber = adminPhoneNumberRepository.GetAdminPhoneNumber(user);
                model.addAttribute("AdminPhoneNumber", adminPhoneNumber);
                return "UserView";
            } catch (EntityNotFoundException e){
                return "UserView";

            }



        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @GetMapping("/{id}")
    public String showUserInfoAdmin(Model model, @PathVariable int id, HttpSession session) {
        try {
            authenticationHelper.tryGetCurrentUser(session);
            User user = userService.get(id);
            List<Post> userPosts = postService.getByCreator(user);
            model.addAttribute("user", user);
            model.addAttribute("posts", userPosts);
            try {

                AdminPhoneNumber adminPhoneNumber = adminPhoneNumberRepository.GetAdminPhoneNumber(user);
                model.addAttribute("AdminPhoneNumber", adminPhoneNumber);
                model.addAttribute("isPhoneNumberAdminView", true);
                return "UserAdminView";
            } catch (EntityNotFoundException e){
                model.addAttribute("isPhoneNumberAdminView", false);
                return "UserAdminView";

            }


        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }


    @GetMapping("/{id}/blocked")
    public String blocked(Model model, @PathVariable int id, HttpSession session) {
        try {

            User user = userService.get(id);


            userService.blockUser(user);


            return "redirect:/users/{id}";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}/unblocked")
    public String Unblocked(Model model, @PathVariable int id, HttpSession session) {
        try {

            User user = userService.get(id);


            userService.unblockUser(user);


            return "redirect:/users/{id}";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @GetMapping("/{id}/makeAdmin")
    public String makeUserAdmin(Model model, @PathVariable int id, HttpSession session) {
        try {
            User admin = authenticationHelper.tryGetCurrentUser(session);

            userService.makeUserAdmin(admin, id, null);
            return "redirect:/users/{id}";

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @GetMapping("/{id}/delete")
    public String delete(Model model, @PathVariable int id, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);

            if (user.getId() != id && user.isAdmin()) {

                userService.delete(id, user);
                return "redirect:/admin";
            }
            session.removeAttribute("currentUser");
            userService.delete(id, user);
            return "redirect:/";

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @PostMapping("/current")
    public String handleRegister(@Valid @ModelAttribute("adminPhoneNumber") AdminPhoneNumberDto adminPhoneNumberDto, HttpSession session,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/users/current";
        }
        try {

            AdminPhoneNumber adminPhoneNumber = new AdminPhoneNumber();
            adminPhoneNumber.setUser(authenticationHelper.tryGetCurrentUser(session));
            adminPhoneNumber.setPhoneNumber(adminPhoneNumberDto.getPhoneNumber());
            userService.addPhoneNumber(adminPhoneNumber);

            return "redirect:/users/current";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("username", "username_error", e.getMessage());
            return "UserView";
        }
    }

    @GetMapping("/{id}/deleteNumber")
    public String deleteNumber(@PathVariable int id, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);


            userService.deletePhoneNumber(user);

            return "redirect:/users/current";

        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}/update")
    public String updateUser( Model model, @PathVariable int id, HttpSession session) {
        try{
            authenticationHelper.tryGetCurrentUser(session);
            User user = userService.get(id);
            UserDto userDto = userMapper.toDto(user);

            model.addAttribute("user", userDto);
            return "UpdateUserView";
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }



    }

    @PostMapping("/{id}/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDto userDto,@PathVariable int id,
                                 BindingResult bindingResult,  HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "UpdateUserView";
        }



        try {
///TODO fix email validation and password
            authenticationHelper.tryGetCurrentUser(session);
            User user = userService.get(id);
            User updatedUser = userMapper.fromDto(userDto);
            updatedUser.setId(user.getId());

            userService.update(user, updatedUser);
            return "redirect:/";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("username", "username_error", e.getMessage());
            return "UpdateUserView";
        }
    }
}
