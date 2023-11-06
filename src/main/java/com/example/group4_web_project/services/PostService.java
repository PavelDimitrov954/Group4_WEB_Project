package com.example.group4_web_project.services;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.User;

import java.util.List;

public interface PostService {
    List<Post> get(FilterOptions filterOptions);    // Monika

    Post get(int id);       // Pavel

    void create(Post post, User user);      // Borko

    void update(User user, Post post);      // Monika

    void delete(User user, int postId);     // Pavel

    int getPostCount();         // Borko
    long getLikesCount(Post post);
    boolean checkForModifyPermissions(User user, Post post);
    void likePost(User user, int postId);
    boolean hasUserLikedPost(Post post, User user);
    void removeLike(User user, int postId);
    void addTagToPost(int postId, Tag tag, User user);
    void removeTagFromPost(int postId, String tagName, User user);
    public List<com.example.group4_web_project.models.Post> getTopCommentedPosts(int limit);
    public List<com.example.group4_web_project.models.Post> getMostRecentPosts(int limit);
}
