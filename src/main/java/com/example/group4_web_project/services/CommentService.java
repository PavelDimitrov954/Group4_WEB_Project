package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.User;

public interface CommentService {

    void create(Comment comment);  // Monika

    void update(User user, Comment comment);  // Pavel

    void delete(User user, int id); // Borko
}
