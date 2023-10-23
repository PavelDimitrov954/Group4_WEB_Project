package com.example.group4_web_project.controllers;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.PostMapper;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.PostDto;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.services.PostService;
import com.example.group4_web_project.models.Post;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Api(tags = "Post Management")
public class PostRestController {

    private final PostService postService;
    private final AuthenticationHelper authenticationHelper;

    private final PostMapper postMapper;

    @Autowired
    public PostRestController(PostService postService, AuthenticationHelper authenticationHelper, PostMapper postMapper) {
        this.postService = postService;
        this.authenticationHelper = authenticationHelper;
        this.postMapper = postMapper;
    }

    @GetMapping
    public List<Post> get(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String createdBy,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
    ) {
        FilterOptions filterOptions = new FilterOptions(title, createdBy, sortBy, sortOrder);
        return postService.get(filterOptions);
    }

    @GetMapping("/{id}")
    public Post get(@PathVariable int id) {
        try {
            return postService.get(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void create(@RequestHeader HttpHeaders headers, @RequestBody @Valid PostDto postDto) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postMapper.fromDto(postDto);
            postService.create(post, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @PutMapping("/{id}")
    public void update(@RequestHeader HttpHeaders headers, @PathVariable int id, @RequestBody @Valid PostDto postDto) {

        try {
            User user = authenticationHelper.tryGetUser(headers);
            Post post = postMapper.fromDto(id, postDto);
            postService.update(user, post);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }


    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            postService.delete(user, id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/all")
    public int getPostCount() {
        return postService.getPostCount();
    }
}
