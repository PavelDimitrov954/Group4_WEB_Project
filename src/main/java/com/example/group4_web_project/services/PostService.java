package com.example.group4_web_project.services;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.User;
import org.hibernate.type.descriptor.jdbc.TinyIntAsSmallIntJdbcType;

import java.util.List;

public interface PostService {
    List<Post> get(FilterOptions filterOptions);    // Monika

    Post get(int id);       // Pavel

    void create(Post post, User user);      // Borko

    void update(User user, Post post);      // Monika

    void delete(User user, int postId);     // Pavel

    int getPostCount();         // Borko
    void likePost(User user, int postId);//TODO
    void removeLike(User user, int postId);//TODO
    void addTagToPost(int postId, Tag tag);
    void removeTagFromPost(int postId, String tagName);
}
