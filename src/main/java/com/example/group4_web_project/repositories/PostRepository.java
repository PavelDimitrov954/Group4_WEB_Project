package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Post;

import java.util.List;

public interface PostRepository {

    List<Post> get(FilterOptions filterOptions);  // Borko TODO
    Post get(int id);   // Monika
    Post get(String title);
    int getPostCount(); // Pavel DONE
    void create(Post post); // Borko DONE
    void update(Post post);  // Monika
    void delete(int id);    // Pavel DONE
}