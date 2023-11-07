package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.Like;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.group4_web_project.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    public static final String ONLY_CREATOR_CAN_MODIFY_A_POST = "Only  creator or admin can modify a post.";
    public static final String INVALID_AUTHORIZATION_DELETE = "IOnly  creator or admin can delete a post.";
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    private final UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository,
                           TagRepository tagRepository, UserService userService) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.tagRepository = tagRepository;
        this.userService = userService;
    }

    public List<Post> get(FilterOptions filterOptions) {

        if(filterOptions.getCreatedBy().isPresent()){
            String username = filterOptions.getCreatedBy().get().trim();

            if(!username.isEmpty()){


                User user = userService.get(filterOptions.getCreatedBy().get());
                filterOptions.setCreatedBy(Optional.ofNullable(Integer.toString(user.getId())));}

            else {
                filterOptions.setCreatedBy(Optional.empty());
            }
        }





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

        postRepository.create(post);
    }

    @Override
    public void update(User user, Post post) {
        boolean duplicateExists = true;
        try {
            Post existingPost = postRepository.get(post.getTitle());
            if (existingPost.getId() == post.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Post", "title", post.getTitle());
        }

        checkModifyPermissions(post.getId(), user);

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
    public boolean checkForModifyPermissions(User user, Post post) {


        return user.getId() == post.getCreatedBy().getId() || user.isAdmin();


    }


    @Override
    public int getPostCount() {
        return postRepository.getPostCount();
    }

    @Override
    public long getLikesCount(Post post) {
        return postRepository.getLikesCount(post);
    }

    @Override
    public List<Post> getByCreator(User user) {
        return postRepository.getByCreator(user);
    }

    @Override
    public void likePost(User user, int postId) {
        Post post = postRepository.get(postId);
        if (postRepository.hasUserLikedPost(post, user)) {
            removeLike(user, postId);
        } else {
            Like like = new Like();
            like.setCreatedBy(user);
            like.setPost(post);
            postRepository.likePost(like);
        }
//        long count = postRepository.getLikesCount(post);
//        System.out.println(count);
    }

    @Override
    public boolean hasUserLikedPost(Post post, User user) {
        return postRepository.hasUserLikedPost(post, user);
    }

    @Override
    public void removeLike(User user, int postId) {
        Post post = postRepository.get(postId);
        if (!postRepository.hasUserLikedPost(post, user)) {
            throw new AuthorizationException("User has not liked this post!");
        } else {
            Like like = postRepository.getLikeByPostAndUser(post, user);
            postRepository.removeLike(like);
        }

//        long count = postRepository.getLikesCount(post);
//        System.out.println(count);
    }

    @Override
    public void addTagToPost(int postId, Tag tag, User user) {
        Post post = postRepository.get(postId);

        if (post == null) {
            throw new EntityNotFoundException("Post", postId);
        }
        if (!user.isAdmin() && !user.getUsername().equals(post.getCreatedBy().getUsername())) {
            throw new AuthorizationException("User not authorized to add tags to this post");
        }

        Tag existingTag = tagRepository.get(tag.getName());
        if (existingTag == null) {
            tagRepository.create(tag);
            post.addTag(tag);

        } else {

            if (!post.getTags().contains(existingTag)) {
                post.addTag(existingTag);
            } else {
                throw new EntityDuplicateException("Post", "tag", tag.getName());
            }
        }
        postRepository.update(post);
    }


    @Override
    public void removeTagFromPost(int postId, String tagName, User user) {
        Post post = postRepository.get(postId);

        if (!user.isAdmin() && !post.getCreatedBy().equals(user)) {
            throw new AuthorizationException("User not authorized to remove tags from this post");
        }
        if (post == null) {
            throw new EntityNotFoundException("Post", postId);
        }
        Tag tag = tagRepository.get(tagName);

        Set<Tag> tags = post.getTags();
        tags.remove(tag);
        post.setTags(tags);

        postRepository.update(post);

        int postsUsingTag = tagRepository.countPostsUsingTag(tagName);
        if (postsUsingTag == 0) {
            tagRepository.delete(tagRepository.get(tagName));
        }
    }


    private void checkModifyPermissions(int postId, User user) {
        Post post = postRepository.get(postId);
        if (!post.getCreatedBy().equals(user) && !user.isAdmin()) {
            throw new AuthorizationException(ONLY_CREATOR_CAN_MODIFY_A_POST);
        }

    }

    @Override
    public List<Post> getTopCommentedPosts(int limit) {
        return postRepository.findTopCommentedPosts(limit);
    }

    @Override
    public List<Post> getMostRecentPosts(int limit) {
        return postRepository.findMostRecentPosts(limit);
    }

}
