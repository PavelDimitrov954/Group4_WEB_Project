package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.User;
import com.example.group4_web_project.models.UserDto;
import com.example.group4_web_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    @Autowired
    public UserMapper(UserService userService) {
    }

    public User fromDto(int id, UserDto dto) {
        User user = fromDto(dto);
        user.setId(id);
        return user;
    }

    public User fromDto(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

}

