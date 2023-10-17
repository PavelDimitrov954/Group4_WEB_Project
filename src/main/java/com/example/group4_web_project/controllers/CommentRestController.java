package com.example.group4_web_project.controllers;

import com.example.group4_web_project.helpers.CommentMapper;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.CommentDto;
import com.example.group4_web_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;


  @Autowired
    public CommentRestController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }


    @PostMapping()
    public void create(@RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.fromDto(commentDto);

        commentService.create(comment);
    }
}