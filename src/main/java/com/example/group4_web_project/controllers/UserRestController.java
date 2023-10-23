package com.example.group4_web_project.controllers;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.UserMapper;
import com.example.group4_web_project.models.*;
import com.example.group4_web_project.services.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
//@Api(tags = "User Management")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationHelper authenticationHelper;


    public UserRestController(UserService userService, UserMapper userMapper, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationHelper = authenticationHelper;
    }




    @GetMapping
    public List<User> get(@RequestHeader HttpHeaders headers,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String firstName

    ) {
            authenticationHelper.tryGetUser(headers);
            FilterOptionsUser filterOptionsUser = new FilterOptionsUser(username, email,firstName);
            return userService.get(filterOptionsUser);

    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        try {

            return userService.get(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/count")
    public int getUserCount() {
        return userService.getUserCount();
    }

    @PostMapping()
    public void register( @Valid @RequestBody UserDto userDto) {
        try {
            User user = userMapper.fromDto(userDto);
            userService.register(user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public void update(@RequestHeader HttpHeaders headers, @PathVariable int id, @RequestBody @Valid UserDto userDto) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            User updateUser = userMapper.fromDto(id, userDto);
            userService.update(user, updateUser);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            userService.delete(id,user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}/admin")
    public void makeUserAdmin(
            @RequestHeader HttpHeaders headers,
            @PathVariable int id,
            @RequestBody(required = false) String phoneNumber
    ) {
        try {
            User adminUser = authenticationHelper.tryGetUser(headers);
            userService.makeUserAdmin(adminUser, id, phoneNumber);
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{userId}/block")
    public void blockUser(@RequestHeader HttpHeaders headers, @PathVariable int userId) {
        try {
            User adminUser = authenticationHelper.tryGetUser(headers);
            User user = userService.get(userId);

            if (adminUser.isAdmin()) {
                userService.blockUser(user);
            } else {
                throw new AuthorizationException("You don't have permission to block users.");
            }
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{userId}/unblock")
    public void unblockUser(@RequestHeader HttpHeaders headers, @PathVariable int userId) {
        try {
            User adminUser = authenticationHelper.tryGetUser(headers);
            User user = userService.get(userId);

            if (adminUser.isAdmin()) {
                userService.unblockUser(user);
            } else {
                throw new AuthorizationException("You don't have permission to unblock users.");
            }
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}