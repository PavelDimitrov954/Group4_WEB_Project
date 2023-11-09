package com.example.group4_web_project.models;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_phone_number")
public class AdminPhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_phone_number_id")
    private int adminPhoneNumberId;

    @Column(name = "phone_number")
    private String phoneNumber;


   // @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public AdminPhoneNumber() {
    }




    public AdminPhoneNumber( String phoneNumber, User user) {
        this.adminPhoneNumberId = adminPhoneNumberId;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public int getAdminPhoneNumberId() {
        return adminPhoneNumberId;
    }

    public void setAdminPhoneNumberId(int adminPhoneNumberId) {
        this.adminPhoneNumberId = adminPhoneNumberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

