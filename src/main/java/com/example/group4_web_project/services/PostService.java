package com.example.group4_web_project.services;

import  com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;

public interface PostService {

    Post get(int id);
    void update(User user, Post post);
    int getPostCount();
}
