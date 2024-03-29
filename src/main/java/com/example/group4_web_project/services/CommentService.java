package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.User;

import java.util.List;

public interface CommentService {

    void create(Comment comment);  // Monika

    void update(User user, Comment comment);  // Pavel

    void delete(User user, int id); // Borko

    List<Comment> getByPostId(int id);
    Comment get(int id);
    boolean checkForModifyPermissionsComment(User user, Comment comment);
}
