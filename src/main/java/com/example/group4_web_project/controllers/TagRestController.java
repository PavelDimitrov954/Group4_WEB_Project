package com.example.group4_web_project.controllers;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.helpers.AuthenticationHelper;
import com.example.group4_web_project.helpers.TagMapper;
import com.example.group4_web_project.models.Tag;
import com.example.group4_web_project.models.TagDto;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.services.TagService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@Api(tags = "Tag Management")
public class TagRestController {

    private final TagService tagService;
    private final TagMapper tagMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public TagRestController(TagService tagService, TagMapper tagMapper, AuthenticationHelper authenticationHelper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{name}")
    public Tag getTag(@PathVariable String name) {
        try {
            return tagService.get(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public void createTag(@RequestHeader HttpHeaders headers, @RequestBody @Valid TagDto tagDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Tag tag = tagMapper.fromDto(tagDto);
            tagService.create(tag);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

//    @PutMapping("/{name}")
//    public void updateTag(@RequestHeader HttpHeaders headers, @PathVariable String name, @RequestBody @Valid TagDto tagDto) {
//        try {
//            User user = authenticationHelper.tryGetUser(headers);
//            Tag tag = tagMapper.fromDto(tagDto);
//            tagService.update(tag);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (EntityDuplicateException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        } catch (AuthorizationException e) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
//        }
//    }

    @DeleteMapping("/{name}")
    public void deleteTag(@RequestHeader HttpHeaders headers, @PathVariable String name) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            tagService.delete(getTag(name));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
