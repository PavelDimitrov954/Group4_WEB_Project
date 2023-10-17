package com.example.group4_web_project.repositories;

import com.example.group4_web_project.exceptions.EntityNotFoundException;

import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

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

        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    @Override
    public User get(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException("Post", id);
            }
            return user;
        }
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

       try (Session session = sessionFactory.openSession()){
        Query query = session.createNativeQuery("select count(*) from users",Integer.class);
        return (int)query.uniqueResult();
       }
       //return get().size();
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

        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();

        }

    }

    @Override
    public void makeUserAdmin(User user, String phoneNumber) {

    }
}
