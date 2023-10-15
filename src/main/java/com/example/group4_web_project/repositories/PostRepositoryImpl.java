package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository{
    @Override
    public List<Post> get() {
        return null;
    }

    @Override
    public List<Post> get(int id) {
        return null;
    }

    @Override
    public void create(Post post) {

    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(int id) {

    }
}
