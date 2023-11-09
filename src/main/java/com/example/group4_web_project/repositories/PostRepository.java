package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Like;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.User;

import java.util.List;
import java.util.Set;

public interface PostRepository {

    List<Post> get(FilterOptions filterOptions);  // Borko
    List<Post> getByCreator(User user);

    Post get(int id);   // Monika

    Post get(String title);

    int getPostCount(); // Pavel DONE

    void create(Post post); // Borko DONE

    void update(Post post);  // Monika
    void delete(Post post);    // Pavel DONE
    void likePost(Like like);
    void removeLike(Like like);
    boolean hasUserLikedPost(Post post, User user);
    Like getLikeByPostAndUser(Post post, User user);
    void addTagToPost(int postId, Tag tag);
    void removeTagsFromPost(int postId);
    long getLikesCount(Post post);
    public List<com.example.group4_web_project.models.Post> findTopCommentedPosts(int limit);
    public List<com.example.group4_web_project.models.Post> findMostRecentPosts(int limit);
}
