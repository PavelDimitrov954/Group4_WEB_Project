package com.example.group4_web_project.models;

import jakarta.persistence.*;

public class AdminPhoneNumberDto {


    private String phoneNumber;


    private int userId;

    public AdminPhoneNumberDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

