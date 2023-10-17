package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> get();    // Borko TODO
    Comment get(int id);    //Borko additional DONE
    void create(Comment comment);   // Monika
    void update(Comment comment);   // Pavel DONE
    void delete(int id);   // Borko DONE
}
