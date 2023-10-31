package com.example.group4_web_project.repositories;

import com.example.group4_web_project.models.Tag;

import java.util.List;

public interface TagRepository {
    List<Tag> getAllTags();

    Tag get(String name);

    void create(Tag tag);

    void update(Tag tag);

    void delete(Tag tag);
    int countPostsUsingTag(String tagName);
}
