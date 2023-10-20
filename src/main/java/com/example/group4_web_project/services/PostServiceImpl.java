package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.group4_web_project.models.Post;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    public static final String ONLY_CREATOR_CAN_MODIFY_A_POST = "Only  creator can modify a post.";
    public static final String INVALID_AUTHORIZATION_DELETE = "IOnly  creator or admin can delete a post.";
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Post> get(FilterOptions filterOptions) {
        return postRepository.get(filterOptions);
    }

    @Override
    public Post get(int id) {
        Post post = postRepository.get(id);
        try {
            post.setComments(commentRepository.getByPostId(id));

        } catch (EntityNotFoundException e) {
            post.setComments(null);
        }
        return post;
        //    return postRepository.get(id);
    }

    @Override
    public void create(Post post, User user) {
        boolean duplicateExists = true;
        try {
            postRepository.get(post.getTitle());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Post", "title", post.getTitle());
        }



        post.setCreatedBy(user);
        post.setCommentsCount(0);
        post.setLikes(0);
        postRepository.create(post);
    }

    @Override
    public void update(User user, Post post) {

        checkModifyPermissions(post.getId(), user);
        post.setLikes(postRepository.get(post.getId()).getLikes());
        post.setCommentsCount(postRepository.get(post.getId()).getCommentsCount());
        postRepository.update(post);
    }

    @Override
    public void delete(User user, int postId) {
        Post post = get(postId);

        if (user.getId() != post.getCreatedBy().getId() && !user.isAdmin()) {
            throw new AuthorizationException(INVALID_AUTHORIZATION_DELETE);
        }
        postRepository.delete(post);

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
