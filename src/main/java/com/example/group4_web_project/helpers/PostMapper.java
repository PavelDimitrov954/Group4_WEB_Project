package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.PostDto;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostMapper(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Post fromDto(int postID, PostDto postDto){

        Post post = postRepository.get(postID);

        Post postToUpdate = new Post();
        postToUpdate.setId(postID);
        postToUpdate.setCreatedBy(post.getCreatedBy());
        postToUpdate.setContent(postDto.getContent());
        postToUpdate.setTitle(postDto.getTitle());


        return postToUpdate;

    }
    public Post fromDto(PostDto postDto){

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return  post;

    }
}
