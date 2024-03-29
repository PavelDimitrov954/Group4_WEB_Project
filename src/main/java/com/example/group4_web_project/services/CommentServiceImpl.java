package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    public static final String ONLY_CREATOR_CAN_MODIFY_A_COMMENT = "Only  creator or admin can modify a comment.";
    public static final String INVALID_AUTHORIZATION = "Invalid authorization";
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public void create(Comment comment) {
       // comment.setCreateDate(LocalDateTime.now());
        commentRepository.create(comment);
    }

    @Override
    public void update(User user, Comment comment) {


        if (!user.equals(comment.getCreatedBy()) && !user.isAdmin()) {
            throw new AuthorizationException(ONLY_CREATOR_CAN_MODIFY_A_COMMENT);
        }


        commentRepository.update(comment);
    }

    @Override
    public void delete(User user, int id) {

        Comment comment = commentRepository.get(id);


        if (!comment.getCreatedBy().equals(user) && !user.isAdmin()) {
            throw new AuthorizationException(INVALID_AUTHORIZATION);
        }

        commentRepository.delete(comment);

    }

    @Override
    public List<Comment> getByPostId(int id) {
        return commentRepository.getByPostId(id);
    }

    @Override
    public Comment get(int id) {
        return commentRepository.get(id);
    }

    @Override
    public boolean checkForModifyPermissionsComment(User user, Comment comment) {
        return comment.getCreatedBy().equals(user) || user.isAdmin();
    }
}
