package com.example.group4_web_project.models;

import net.bytebuddy.dynamic.DynamicType;

import java.util.Optional;

public class FilterOptionsUser {

    private Optional<String> username;
     private Optional<String> email;

     private Optional<String> firstName;


    public FilterOptionsUser(String username, String email,String firstName) {
        this.username = Optional.ofNullable(username);
        this.email = Optional.ofNullable(email);
        this.firstName = Optional.ofNullable(firstName);
    }



    public Optional<String> getUsername() {
        return username;
    }

    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getFirstName() {
        return firstName;
    }

    public void setUsername(Optional<String> username) {
        this.username = username;
    }

    public void setEmail(Optional<String> email) {
        this.email = email;
    }

    public void setFirstName(Optional<String> firstName) {
        this.firstName = firstName;
    }
}
