package com.example.group4_web_project.models;


import java.time.LocalDateTime;

public class PostDto {

    private String title;

    private String content;
    private LocalDateTime createDate;

    public PostDto() {
        this.createDate = LocalDateTime.now();
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
