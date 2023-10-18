package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Comment;

import java.util.List;

public interface CommentRepository {



    List<Comment> getByPostId(int id); //Borko
    List<Comment> getByUserId(int id); //Borko
    void create(Comment comment);   // Monika
    void update(Comment comment);   // Pavel DONE
    void delete(Comment comment);   // Borko DONE

    Comment get(int id);
}
