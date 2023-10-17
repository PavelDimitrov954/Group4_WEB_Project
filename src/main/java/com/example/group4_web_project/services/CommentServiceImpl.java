package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.repositories.CommentRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public void create(Comment comment) {
        commentRepository.create(comment);


    }

    @Override
    public void update(Comment comment) {
        commentRepository.update(comment);
    }
}
