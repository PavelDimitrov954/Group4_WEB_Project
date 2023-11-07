package com.example.group4_web_project.models;

import java.util.Optional;

public class FilterDtoUser {
    private String username;
    private String email;

    private String firstName;

    public FilterDtoUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
