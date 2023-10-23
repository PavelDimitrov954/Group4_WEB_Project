package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.Post;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.CommentRepository;
import com.example.group4_web_project.repositories.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.group4_web_project.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository mockRepository;

    @InjectMocks
    PostServiceImpl service;


    @Test
    void get_Should_CallRepository() {
        // Arrange
        FilterOptions mockFilterOptions = createMockFilterOptions();
        Mockito.when(mockRepository.get(mockFilterOptions))
                .thenReturn(null);

        // Act
        service.get(mockFilterOptions);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .get(mockFilterOptions);
    }


    @Test
    void update_Should_CallRepository() {

        Post mockPost = createMockPost();
        User mockUser = createMockUser();


        Mockito.when(mockRepository.get(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        Mockito.when(mockRepository.get(Mockito.anyInt()))
                .thenReturn(mockPost);

        service.update(mockUser,mockPost);

        Mockito.verify(mockRepository, Mockito.times(1))
                .update(mockPost);
    }





    @Test
    public void update_Should_ThrowException_When_UserIsNotCreatorOrAdmin() {

        Post mockPost = createMockPost();

        Mockito.when(mockRepository.get(mockPost.getTitle()))
                .thenThrow(EntityNotFoundException.class);

        Mockito.when(mockRepository.get(mockPost.getId()))
                .thenReturn(mockPost);


        Assertions.assertThrows(
                AuthorizationException.class,
                () -> service.update(Mockito.mock(User.class), mockPost));
    }

    @Test
    public void update_Should_ThrowException_When_PostTitleExist() {

        Post mockPost = createMockPost();
        User mockUser = createMockUser();

        Mockito.when(mockRepository.get(mockPost.getTitle()))
                .thenReturn(mockPost);

        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> service.update(mockUser, mockPost));
    }


}


