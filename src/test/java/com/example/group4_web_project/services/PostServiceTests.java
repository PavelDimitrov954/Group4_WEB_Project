package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.*;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

    @Test
    public void create_Should_ThrowException_When_PostTitleExist() {

        Post mockPost = createMockPost();
        User mockUser = createMockUser();

        Mockito.when(mockRepository.get(mockPost.getTitle()))
                .thenReturn(mockPost);

        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> service.create(mockPost, mockUser));
    }
    @Test
    void create_Should_CallRepository() {

        Post mockPost = createMockPost();

        User mockUser = createMockUser();


        Mockito.when(mockRepository.get(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);



        service.create(mockPost, mockUser);

        Mockito.verify(mockRepository, Mockito.times(1))
                .create(mockPost);
    }


    @Test
    void getPostCount_Should_CallRepository() {





        service.getPostCount();

        Mockito.verify(mockRepository, Mockito.times(1))
                .getPostCount();
    }

    @Test
    public void like_ShouldThrowAuthorizationException() {
        //TODO
        when(mockRepository.hasUserLikedPost( Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(true);
        assertThrows(AuthorizationException.class, () -> service.likePost( Mockito.any(User.class), Mockito.anyInt()));
    }

    @Test
    public void like_Should_Call_Repository() {
        //TODO
        when(mockRepository.hasUserLikedPost( Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(false);
        service.likePost( Mockito.any(User.class), Mockito.anyInt());
        verify(mockRepository, times(1)).likePost(Mockito.any(Like.class));
    }
    @Test
    public void remove_like_ShouldThrowAuthorizationException() {
        //TODO
        when(mockRepository.hasUserLikedPost( Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(true);
        assertThrows(AuthorizationException.class, () -> service.likePost( Mockito.any(User.class), Mockito.anyInt()));
    }

    @Test
    public void remove_like_Should_Call_Repository() {
        //TODO
        when(mockRepository.hasUserLikedPost( Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(false);
        service.likePost( Mockito.any(User.class), Mockito.anyInt());
        verify(mockRepository, times(1)).likePost(Mockito.any(Like.class));
    }

}


