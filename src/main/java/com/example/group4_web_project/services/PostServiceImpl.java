package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.group4_web_project.models.Post;

@Service
public class PostServiceImpl implements PostService {

    public static final String ONLY_CREATOR_CAN_MODIFY_A_POST = "Only  creator can modify a post.";
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Post get(int id) {
        Post post = postRepository.get(id);
        try {
            post.setComments(commentRepository.getByPostId(id));

        } catch (EntityNotFoundException e){
            post.setComments(null);
        }
        return post;
    //    return postRepository.get(id);
    }

    @Override
    public void update(User user, Post post) {

        checkModifyPermissions(post.getId(), user);

        postRepository.update(post);
    }


    @Override
    public int getPostCount() {
        return postRepository.getPostCount();
    }

    private void checkModifyPermissions(int postId, User user) {
        Post post = postRepository.get(postId);
        if (!post.getCreatedBy().equals(user)) {
            throw new AuthorizationException(ONLY_CREATOR_CAN_MODIFY_A_POST);
        }

    }
}
