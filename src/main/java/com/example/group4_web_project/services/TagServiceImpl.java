package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.getAllTags();
    }

    @Override
    public Tag get(String name) {
        return tagRepository.get(name);
    }

    @Override
    public void create(Tag tag) {
        Tag existingTag = tagRepository.get(tag.getName());
        if (existingTag != null) {
            throw new EntityDuplicateException("Tag", "name", tag.getName());
        }

        tagRepository.create(tag);
    }


    @Override
    public void delete(Tag tag) {
        Tag existingTag = tagRepository.get(tag.getName());
        if (existingTag == null) {
            throw new EntityNotFoundException("Tag", "id", String.valueOf(tag.getId()));
        }
        tagRepository.delete(existingTag);
    }
}
