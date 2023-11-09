package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.User;

public interface AdminPhoneNumberRepository {

    void createAdminPhoneNumber(User user, String phoneNumber);
    String GetPhoneNumber(User user);
}
