package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.repositories.CommentRepository;
import org.junit.jupiter.api.Assertions;
import com.example.group4_web_project.Helpers;
import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.group4_web_project.Helpers.createMockComment;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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


    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void update_ShouldUpdateCommentByAdmin() {
        User adminUser = Helpers.createMockAdmin();
        Comment comment = Helpers.createMockComment();
        when(commentRepository.get(comment.getId())).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.update(adminUser, comment));
        verify(commentRepository, times(1)).update(comment);
    }

    @Test
    public void update_ShouldUpdateCommentByCreator() {
        User creatorUser = Helpers.createMockUser();
        Comment comment = Helpers.createMockComment();
        comment.setCreatedBy(creatorUser);
        when(commentRepository.get(comment.getId())).thenReturn(comment);

        assertDoesNotThrow(() -> commentService.update(creatorUser, comment));
        verify(commentRepository, times(1)).update(comment);
    }

    @Test
    public void update_ShouldThrowAuthorizationException() {
        Comment comment = Helpers.createMockComment();
        when(commentRepository.get(comment.getId())).thenReturn(comment);

        User unauthorizedUser = Helpers.createMockUser();
        assertThrows(AuthorizationException.class, () -> commentService.update(unauthorizedUser, comment));
    }
    @Test
    public void delete_ShouldThrowAuthorizationException() {
        Comment comment = Helpers.createMockComment();
        when(mockRepository.get(comment.getId())).thenReturn(comment);

        User unauthorizedUser = Helpers.createMockUser();
        unauthorizedUser.setId(123);
        assertThrows(AuthorizationException.class, () -> commentService.delete(unauthorizedUser, comment.getId()));
    }
    @Test
    public void delete_Should_CallRepository() {
        User creatorUser = Helpers.createMockUser();
        Comment comment = Helpers.createMockComment();
        comment.setCreatedBy(creatorUser);


        when(mockRepository.get(comment.getId())).thenReturn(comment);
        service.delete(creatorUser, comment.getId());

        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(comment);

    }
}
