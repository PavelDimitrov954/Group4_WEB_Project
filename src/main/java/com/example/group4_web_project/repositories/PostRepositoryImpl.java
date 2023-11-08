package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.*;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
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

//                filterOptions.getTag().ifPresent(value -> {
//
//                });


            StringBuilder queryString = new StringBuilder("select comment.post from Comment comment right join comment.post post ");
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
            System.out.println(queryString);

            Query<Post> query = session.createQuery(queryString.toString(), Post.class);
                
                query.setProperties(params);
                return query.list();
            }
        }
    }

    @Override
    public List<Post> getByCreator(User user) {

        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("from Post where createdBy = :user", Post.class);
            query.setParameter("user", user);

            List<Post> result = query.list();

            return result;
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
        try (Session session = sessionFactory.openSession()) {
            Number postCount = (Number) session.createNativeQuery("SELECT COUNT(*) FROM posts")
                    .uniqueResult();

            return postCount != null ? postCount.intValue() : 0;
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
    public void likePost(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(like);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeLike(Like like) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(like);
            session.getTransaction().commit();
        }
    }




    @Override
    public boolean hasUserLikedPost(Post post, User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Like> query = session.createQuery("from Like like where like.createdBy.id = :user_id and like.post.id = :post_id", Like.class);
            query.setParameter("user_id", user.getId());
            query.setParameter("post_id", post.getId());

            List<Like> result = query.list();
            if (result.size() == 0) {
               return false;
            }

            return true;
        }

    }

    @Override
    public Like getLikeByPostAndUser(Post post, User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Like> query = session.createQuery("from Like like where like.createdBy.id = :user_id and like.post.id = :post_id", Like.class);
            query.setParameter("user_id", user.getId());
            query.setParameter("post_id", post.getId());

            List<Like> result = query.list();

            return result.get(0);
        }
    }

    @Override
    public long getLikesCount(Post post) {
        try (Session currentSession = sessionFactory.openSession()) {
            Session session = sessionFactory.getCurrentSession();
            Query query = session
                    .createQuery("SELECT COUNT(*) FROM Like likes where likes.post.id = :post_id");
            query.setParameter("post_id", post.getId());
            long result = (Long) query.uniqueResult();


            return result;
        }
    }



    @Override
    public void addTagToPost(int postId, Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Post post = session.get(Post.class, postId);
            if (post != null) {
                post.getTags().add(tag);
                session.merge(post);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeTagFromPost(int postId, Tag tag) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Post post = session.get(Post.class, postId);
            if (post != null) {
                post.getTags().remove(tag);
                session.merge(post);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Post> findTopCommentedPosts(int limit) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Post p ORDER BY SIZE(p.comments) DESC", Post.class)
                .setMaxResults(limit)
                .list();
    }

    @Override
    public List<Post> findMostRecentPosts(int limit) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Post p ORDER BY p.createDate DESC", Post.class)
                .setMaxResults(limit)
                .list();
    }

    private String generateOrderBy(FilterOptions filterOptions) {
        if (filterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderBy = "";
       // System.out.println(filterOptions.getSortBy().get());
        switch (filterOptions.getSortBy().get()) {
            case "date":
                orderBy = "createDate";
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

