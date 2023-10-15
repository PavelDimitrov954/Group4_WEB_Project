package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Post;

import java.util.List;

public interface PostRepository {

    List<Post> get();
    List<Post> get(int id);

    void create(Post post);

    void update(Post post);

    void delete(int id);


}
