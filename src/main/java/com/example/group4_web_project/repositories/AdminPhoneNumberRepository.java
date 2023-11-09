package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.AdminPhoneNumber;
import com.example.group4_web_project.models.User;

public interface AdminPhoneNumberRepository {

    void createAdminPhoneNumber(User user, String phoneNumber);
    String GetPhoneNumber(User user);
    public void addPhoneNumber(AdminPhoneNumber adminPhoneNumber);

    void delete(AdminPhoneNumber adminPhoneNumber);
    public AdminPhoneNumber GetAdminPhoneNumber(User user);
}
