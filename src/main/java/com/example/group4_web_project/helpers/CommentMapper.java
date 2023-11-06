package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.CommentDto;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

;

@Component
public class CommentMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public CommentMapper(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    public Comment createFromDto(int userId, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCreatedBy(userRepository.get(userId));
        comment.setCreateDate(commentDto.getCreateDate());
        //System.out.println();
       // comment.setPost(postRepository.get(commentDto.getPostId()));

        return comment;
    }

    public Comment updateFromDto(int commentId, CommentDto commentDto) {

        Comment comment = commentRepository.get(commentId);
        comment.setContent(commentDto.getContent());
        return comment;
    }

}