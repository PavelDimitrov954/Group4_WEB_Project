package com.example.group4_web_project.models;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

    private String title;

    private String content;
    private LocalDateTime createDate;
    private Set<String> tagNames = new HashSet<>();

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

    public Set<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(Set<String> tagNames) {
        this.tagNames = tagNames;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
