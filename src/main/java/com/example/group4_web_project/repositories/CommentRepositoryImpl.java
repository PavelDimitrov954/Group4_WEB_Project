package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository{
    @Override
    public List<Comment> get() {
        return null;
    }

    @Override
    public void create(Comment comment) {

    }

    @Override
    public void update(Comment comment) {

    }
}
