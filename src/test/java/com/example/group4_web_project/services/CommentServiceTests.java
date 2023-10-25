package com.example.group4_web_project.services;

import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.Helpers;
import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.models.User;
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
        // Arrange
        Comment mockComment = createMockComment();

        // Act
        service.create(mockComment);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1)).create(mockComment);
    }

    @Test
    public void update_ShouldUpdateCommentByAdmin() {
        // Arrange
        User adminUser = Helpers.createMockAdmin();
        Comment comment = createMockComment();

        // Act
        service.update(adminUser, comment);

        // Assert
        verify(mockRepository, times(1)).update(comment);
    }

    @Test
    public void update_ShouldUpdateCommentByCreator() {
        // Arrange
        User creatorUser = Helpers.createMockUser();
        Comment comment = Helpers.createMockComment();
        comment.setCreatedBy(creatorUser);

        // Act
        service.update(creatorUser, comment);

        // Assert
        Mockito.verify(mockRepository, times(1)).update(comment);
    }

    @Test
    public void update_ShouldThrowAuthorizationException() {
        // Arrange
        Comment comment = Helpers.createMockComment();
        User unauthorizedUser = Helpers.createMockUser();
        unauthorizedUser.setId(2);

        // Act, Assert
        assertThrows(AuthorizationException.class, () -> service.update(unauthorizedUser, comment));
    }

    @Test
    public void delete_ShouldThrowAuthorizationException() {
        Comment comment = Helpers.createMockComment();
        when(mockRepository.get(comment.getId())).thenReturn(comment);

        User unauthorizedUser = Helpers.createMockUser();
        unauthorizedUser.setId(123);
        assertThrows(AuthorizationException.class, () -> service.delete(unauthorizedUser, comment.getId()));
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
