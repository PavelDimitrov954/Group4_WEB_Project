package com.example.group4_web_project.repositories;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<User> get() {
        return null;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User get(String username) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where username = :username", User.class);
            query.setParameter("username", username);

            List<User> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("User", "username", username);
            }

            return result.get(0);
        }
    }

    @Override
    public int getUserCount() {
        return 0;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void register(User user, String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            //TODO save phone number
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void makeUserAdmin(User user, String phoneNumber) {

    }
}
