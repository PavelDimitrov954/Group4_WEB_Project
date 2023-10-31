package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag get(String name);

    void create(Tag tag);

    void delete(Tag tag);
}
