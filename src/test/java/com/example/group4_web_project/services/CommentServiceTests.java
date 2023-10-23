package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.repositories.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.group4_web_project.Helpers.createMockComment;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {

    @Mock
    CommentRepository mockRepository;

    @InjectMocks
    CommentServiceImpl service;

    @Test
    public void create_Should_CallRepository() {

        Comment mockComment = createMockComment();

        service.create(mockComment);

        Mockito.verify(mockRepository, Mockito.times(1))
               .create(mockComment);

    }


}
