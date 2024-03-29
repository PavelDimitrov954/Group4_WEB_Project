package com.example.group4_web_project.repositories;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.AdminPhoneNumber;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public String GetPhoneNumber(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<AdminPhoneNumber> query = session.createQuery(
       "  from AdminPhoneNumber where user = :user", AdminPhoneNumber.class);
            query.setParameter("user", user);

            List<AdminPhoneNumber> result = query.list();
            if(result.isEmpty()){
            throw new EntityNotFoundException("user",user.getId());
            }
            return result.get(0).getPhoneNumber();
        }


        }

    @Override
    public void addPhoneNumber(AdminPhoneNumber adminPhoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            System.out.println(adminPhoneNumber.getPhoneNumber());
            System.out.println(adminPhoneNumber.getUser().getUsername());
            session.beginTransaction();

            session.persist(adminPhoneNumber);

            session.getTransaction().commit();
        }


    }
    @Override
    public AdminPhoneNumber GetAdminPhoneNumber(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<AdminPhoneNumber> query = session.createQuery(
                    "  from AdminPhoneNumber where user = :user", AdminPhoneNumber.class);
            query.setParameter("user", user);

            List<AdminPhoneNumber> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("user", user.getId());
            }
            return result.get(0);
        }
    }

    @Override
    public void delete(AdminPhoneNumber adminPhoneNumber) {
        try (Session session = sessionFactory.openSession()) {

            session.beginTransaction();
            session.remove(adminPhoneNumber);
            session.getTransaction().commit();

        }

    }
}
