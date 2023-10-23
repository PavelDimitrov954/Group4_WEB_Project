package com.example.group4_web_project.models;


public class CommentDto {

    private String content;

    private int postId;


    public CommentDto() {
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
}
