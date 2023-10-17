package com.example.group4_web_project.controllers;

import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.services.PostService;
import com.example.group4_web_project.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    private final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/{id}")
    public Post get(@PathVariable int id) {
        try {
            return postService.get(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Post post) {
        postService.update(post);

    }

    @GetMapping("/all")
    public int getPostCount() {
        return postService.getPostCount();
    }
}
