package com.example.group4_web_project.models;


import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class CommentDto {
    @NotEmpty
    private String content;

    private LocalDateTime createDate;

    private int postId;


    public CommentDto() {
        this.createDate = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public int getPostId() {
        return postId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }




}
