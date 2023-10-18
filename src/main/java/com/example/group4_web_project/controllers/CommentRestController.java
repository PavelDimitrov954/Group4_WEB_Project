package com.example.group4_web_project.controllers;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.CommentMapper;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.CommentDto;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final AuthenticationHelper authenticationHelper;


  @Autowired
    public CommentRestController(CommentService commentService,
                                 CommentMapper commentMapper, AuthenticationHelper authenticationHelper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
      this.authenticationHelper = authenticationHelper;
  }


    @PostMapping()
    public void create(@RequestHeader HttpHeaders headers, @RequestBody CommentDto commentDto) {
      try{
        User user = authenticationHelper.tryGetUser(headers);
        Comment comment = commentMapper.fromDto(user.getId(),commentDto);
        commentService.create(comment);
      }catch (EntityNotFoundException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
      } catch (AuthorizationException e) {
          throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
      }

    }

//    @PutMapping
//    public void update(@RequestBody CommentDto commentDto) {
//        try {
//            Comment comment = commentMapper.fromDto(commentDto);
//            commentService.update(comment);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (EntityDuplicateException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        } catch (AuthorizationException e) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
//        }
//    }
}