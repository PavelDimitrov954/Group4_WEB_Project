package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class TagRepositoryImpl implements TagRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public TagRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tag> getAllTags() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Tag";
            Query<Tag> query = session.createQuery(hql, Tag.class);
            return query.list();
        }
    }

    @Override
    public Tag get(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Tag where lower(name) = :name", Tag.class)
                    .setParameter("name", name.toLowerCase())
                    .uniqueResult();
        }
    }

    @Override
    public void create(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(tag);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(tag);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(tag);
            session.getTransaction().commit();
        }
    }

    @Override
    public int countPostsUsingTag(String tagName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT count(p) FROM Post p JOIN p.tags t WHERE lower(t.name) = :tagName",
                    Long.class);
            query.setParameter("tagName", tagName.toLowerCase());
            return Math.toIntExact(query.uniqueResult());
        }
    }
}
