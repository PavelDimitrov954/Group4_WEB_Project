package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final SessionFactory sessionFactory;

    public PostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Post> get(FilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {
            if (filterOptions.getSortBy().isPresent() && filterOptions.getSortBy().get().equals("commentsCount")) {
                System.out.println("AAAAAAAAAAAAAAA");
                List<String> filters = new ArrayList<>();
                Map<String, Object> params = new HashMap<>();
                filterOptions.getTitle().ifPresent(value -> {
                    filters.add("title like :title");
                    params.put("title", String.format("%%%s%%", value));
                });
                filterOptions.getCreatedBy().ifPresent(value -> {
                    filters.add("createdBy.id = :user_id");
                    params.put("user_id", value);
                });


            StringBuilder queryString = new StringBuilder("select count(comment), comment.post from Comment comment right join comment.post post ");
            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }

            queryString.append(" group by post order by count(comment)");
            if (filterOptions.getSortOrder().isPresent() && filterOptions.getSortOrder().get().equals("desc")){
                queryString.append(" desc");
            }
            Query<Post> query = session.createQuery(queryString.toString());
//

                query.setProperties(params);
                return query.list();
            } else {


                List<String> filters = new ArrayList<>();
                Map<String, Object> params = new HashMap<>();
                filterOptions.getTitle().ifPresent(value -> {
                    filters.add("title like :title");
                    params.put("title", String.format("%%%s%%", value));
                });
                filterOptions.getCreatedBy().ifPresent(value -> {
                    filters.add("createdBy.id = :user_id");
                    params.put("user_id", value);
                });


            StringBuilder queryString = new StringBuilder("from Post");
            if (!filters.isEmpty()) {
                queryString
                        .append(" where ")
                        .append(String.join(" and ", filters));
            }

            queryString.append(generateOrderBy(filterOptions));
            Query<Post> query = session.createQuery(queryString.toString(), Post.class);
                
                query.setProperties(params);
                return query.list();
            }
        }
    }

    @Override
    public Post get(int id) {
        try (Session session = sessionFactory.openSession()) {
            Post post = session.get(Post.class, id);
            if (post == null) {
                throw new EntityNotFoundException("Post", id);
            }
            return post;
        }
    }

    @Override
    public Post get(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("from Post where title = :title", Post.class);
            query.setParameter("title", title);

            List<Post> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("Post", "title", title);
            }

            return result.get(0);
        }
    }

    @Override
    public int getPostCount() {
        try (Session currentSession = sessionFactory.openSession()) {
            // Create a native SQL query to count the posts
            Query<?> countQuery = currentSession.createNativeQuery("SELECT COUNT(*) FROM posts");

            // Execute the query and get the result as a BigInteger
            Object result = countQuery.uniqueResult();

            // Convert the result to an integer
            int postCount = (result instanceof Number) ? ((Number) result).intValue() : 0;

            return postCount;
        }
    }


    @Override
    public void create(Post post) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Post post) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();

        }

    }

    @Override
    public void delete(Post post) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(post);
            session.getTransaction().commit();
        }
    }

    @Override
    public void increaseCommentCount(Post post) {
        try (Session session = sessionFactory.openSession()) {
            post.setCommentsCount(post.getCommentsCount()+1);
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();

        }
    }

    @Override
    public void decreaseCommentCount(Post post) {
        try (Session session = sessionFactory.openSession()) {
            post.setCommentsCount(post.getCommentsCount()-1);
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();

        }
    }

    @Override
    public void likePost(Post post, User user) {

    }

    @Override
    public void removeLike(Post post, User user) {

    }

    @Override
    public void hasUserLikedPost(Post post, User user) {

    }

    private String generateOrderBy(FilterOptions filterOptions) {
        if (filterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderBy = "";
        System.out.println(filterOptions.getSortBy().get());
        switch (filterOptions.getSortBy().get()) {
            case "post_id":
                orderBy = "id";
                break;
            default:
                return "";
        }

        orderBy = String.format(" order by %s", orderBy);

        if (filterOptions.getSortOrder().isPresent() && filterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }

        return orderBy;
    }
}

