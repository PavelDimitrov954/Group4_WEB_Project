package com.example.group4_web_project.repositories;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final SessionFactory sessionFactory;
    private final PostRepository postRepository;


    @Autowired
    public CommentRepositoryImpl(SessionFactory sessionFactory, PostRepository postRepository) {
        this.sessionFactory = sessionFactory;
        this.postRepository = postRepository;
    }


    public Comment get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Comment comment = session.get(Comment.class, id);
            if (comment == null) {
                throw new EntityNotFoundException("Comment", id);
            }
            return comment;
        }
    }

    @Override
    public List<Comment> getByPostId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("from Comment where post.id = :post_id", Comment.class);
            query.setParameter("post_id", id);

            List<Comment> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("Comment", "post_id", Integer.toString(id));
            }

            return result;
        }
    }

    @Override
    public List<Comment> getByUserId(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Comment> query = session.createQuery("from Comment where post.createdBy.id = :user_id", Comment.class);
            query.setParameter("user_id", id);

            List<Comment> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("Comment", "user_id", Integer.toString(id));
            }

            return result;
        }

    }


    @Override
    public void create(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(comment);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(Comment comment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(comment);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Comment comment) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(comment);
            session.getTransaction().commit();
        }
    }


}
