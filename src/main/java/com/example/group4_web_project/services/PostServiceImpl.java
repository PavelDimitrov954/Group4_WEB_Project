package com.example.group4_web_project.services;

import com.example.group4_web_project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.group4_web_project.models.Post;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post get(int id) {
        return postRepository.get(id);
    }

    @Override
    public void update(Post post) {
     postRepository.update(post);
    }
}
