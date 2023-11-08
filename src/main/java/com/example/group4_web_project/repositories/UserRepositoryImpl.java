package com.example.group4_web_project.repositories;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired


    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }




    @Override
    public User get(int id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            if (user == null) {
                throw new EntityNotFoundException("User", id);
            }
            return user;
        }
    }


    @Override
    public List <User> get(FilterOptionsUser filterOptionsUser) {

        try(Session session = sessionFactory.openSession()){
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();


            filterOptionsUser.getUsername().ifPresent(value -> {

                    filters.add(" username like :username");
                    params.put("username", String.format("%%%s%%", value.trim()));



            });



            filterOptionsUser.getEmail().ifPresent(value -> {

                    filters.add(" email like :email ");
                    params.put("email", String.format("%%%s%%",value.trim()));

            });

            filterOptionsUser.getFirstName().ifPresent(value -> {

                    filters.add(" firstName like :firstName ");
                    params.put("firstName", String.format("%%%s%%",value.trim()));

            });

            StringBuilder queryString = new StringBuilder();
            queryString.append("from User ");

            if(!filters.isEmpty()){
                queryString.append(" where ").append(String.join(" and ", filters ));
            }

            System.out.println(queryString.toString());
            params.values().stream().forEach(e-> System.out.println(e + "1"));
            Query<User> query = session.createQuery(queryString.toString(),User.class);

            query.setProperties(params);


            return query.list();
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

        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNativeQuery("select count(*) from users", Integer.class);
            return (int) query.uniqueResult();
        }

    }

    @Override
    public void register(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();

        }

    }


    @Override
    public void delete(User user) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();

        }

    }

    @Override
    public void makeUserAdmin(User user, String phoneNumber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            user.setAdmin(true);

            session.persist(phoneNumber);
            session.merge(user);

            session.getTransaction().commit();
        }
    }
}
