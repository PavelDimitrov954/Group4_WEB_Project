package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.CommentDto;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

;

@Component
public class CommentMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentMapper(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public Comment fromDto(int userId, CommentDto commentDto){

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCreatedBy(userRepository.get(userId));
        System.out.println(commentDto.getPostId());
        comment.setPost(postRepository.get(commentDto.getPostId()));
        return comment;
    }
}