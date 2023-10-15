package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> get();

    void create(Comment comment);

    void update(Comment comment);



}
