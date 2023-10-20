package com.example.group4_web_project.services;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import org.hibernate.type.descriptor.jdbc.TinyIntAsSmallIntJdbcType;

import java.util.List;

public interface PostService {
    List<Post> get(FilterOptions filterOptions);

    Post get(int id);

    void create(Post post, User user);

    void update(User user, Post post);
    void delete(User user, int postId);

    int getPostCount();
}
