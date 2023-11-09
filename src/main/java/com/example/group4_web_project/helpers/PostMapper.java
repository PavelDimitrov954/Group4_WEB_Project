package com.example.group4_web_project.helpers;

import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.PostDto;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.TagRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    private final PostRepository postRepository;

    public PostMapper(UserRepository userRepository, PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public Post fromDto(int postID, PostDto postDto) {
        Post post = postRepository.get(postID);
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());

        return post;
    }

    public Post fromDto(PostDto postDto) {

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreateDate(postDto.getCreateDate());
        return post;
    }

    public PostDto toDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreateDate(post.getCreateDate());
        return postDto;
    }
}
