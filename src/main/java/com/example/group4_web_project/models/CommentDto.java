package com.example.group4_web_project.models;


import java.time.LocalDateTime;

public class CommentDto {

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
