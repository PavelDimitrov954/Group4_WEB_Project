package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.*;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.Like;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    PostRepository mockRepository;

    @Mock
    CommentRepository commentRepository;

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

        service.update(mockUser, mockPost);

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
        when(mockRepository.hasUserLikedPost(Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(true);
        assertThrows(AuthorizationException.class, () -> service.likePost(Mockito.any(User.class), Mockito.anyInt()));
    }

    @Test
    public void like_Should_Call_Repository() {
        //TODO
        when(mockRepository.hasUserLikedPost(Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(false);
        service.likePost(Mockito.any(User.class), Mockito.anyInt());
        verify(mockRepository, times(1)).likePost(Mockito.any(Like.class));
    }

    @Test
    public void remove_like_ShouldThrowAuthorizationException() {
        //TODO
        when(mockRepository.hasUserLikedPost(Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(true);
        assertThrows(AuthorizationException.class, () -> service.likePost(Mockito.any(User.class), Mockito.anyInt()));
    }

    @Test
    public void remove_like_Should_Call_Repository() {
        //TODO
        when(mockRepository.hasUserLikedPost(Mockito.any(Post.class), Mockito.any(User.class))).thenReturn(false);
        service.likePost(Mockito.any(User.class), Mockito.anyInt());
        verify(mockRepository, times(1)).likePost(Mockito.any(Like.class));
    }

    @Test
    public void get_Should_ReturnPost_When_MatchExists() {
        // Arrange
        int postId = 1;
        Post expectedPost = createMockPost();

        // Act
        Post result = createMockPost();

        // Assert
        Assertions.assertEquals(expectedPost, result);
    }

    @Test
    public void delete_Should_DeletePost_When_UserIsAuthorized() {
        // Arrange
        int postId = 1;
        User user = createMockUser();
        Post post = new Post();
        post.setCreatedBy(user);
        Mockito.when(mockRepository.get(postId)).thenReturn(post);

        // Act
        service.delete(user, postId);

        // Assert
        Mockito.verify(mockRepository).delete(post);
    }

    @Test
    public void delete_Should_ThrowAuthorizationException_When_UserIsNotAuthorized() {
        // Arrange
        int postId = 1;
        User user = createMockUser();
        Post post = new Post();
        User user1 = createMockUser();
        user1.setId(2);
        post.setCreatedBy(user1);
        Mockito.when(mockRepository.get(postId)).thenReturn(post);

        // Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> service.delete(user, postId));
    }

    @Test
    public void removeLike_Should_RemoveLike_When_UserHasLiked() {
        // Arrange
        int postId = 1;
        User user = createMockUser();
        Post post = new Post();
        post.setId(postId);
        Like like = new Like();
        like.setCreatedBy(user);
        like.setPost(post);

        Mockito.when(mockRepository.get(postId)).thenReturn(post);
        Mockito.when(mockRepository.hasUserLikedPost(post, user)).thenReturn(true);
        Mockito.when(mockRepository.getLikeByPostAndUser(post, user)).thenReturn(like);

        // Act
        service.removeLike(user, postId);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1)).removeLike(like);
    }

    @Test
    public void removeLike_Should_ThrowAuthorizationException_When_UserHasNotLiked() {
        // Arrange
        int postId = 1;
        User user = createMockUser();
        Post post = new Post();
        post.setId(postId);
        Mockito.when(mockRepository.get(postId)).thenReturn(post);
        Mockito.when(mockRepository.hasUserLikedPost(post, user)).thenReturn(false);

        // Act, Assert
        Assertions.assertThrows(AuthorizationException.class, () -> service.removeLike(user, postId));
    }
}


