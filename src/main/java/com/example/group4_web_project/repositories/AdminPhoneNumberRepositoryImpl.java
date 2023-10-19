package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.AdminPhoneNumber;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminPhoneNumberRepositoryImpl implements AdminPhoneNumberRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public AdminPhoneNumberRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createAdminPhoneNumber(User user, String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            AdminPhoneNumber adminPhoneNumber = new AdminPhoneNumber();
            adminPhoneNumber.setUser(user);
            adminPhoneNumber.setPhoneNumber(phoneNumber);

            session.persist(adminPhoneNumber);

            session.getTransaction().commit();
        }
    }
}
