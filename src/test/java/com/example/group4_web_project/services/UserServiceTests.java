package com.example.group4_web_project.services;

import com.example.group4_web_project.Helpers;
import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.PostRepository;
import com.example.group4_web_project.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.group4_web_project.Helpers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository mockRepository;

    @InjectMocks
    UserServiceImpl service;


    @Test
    void get_Should_CallRepository() {
        // Arrange
        FilterOptionsUser mockFilterOptions = createMockFilterOptionsUser();
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

        User mockUserForUpdate = createMockUser();

        User mockUser = createMockUser();

        service.update(mockUser, mockUserForUpdate);

        Mockito.verify(mockRepository, Mockito.times(1))
                .update(mockUserForUpdate);
    }





    @Test
    public void update_Should_ThrowAuthorizationException() {

        User mockUser = createMockUser();

        Assertions.assertThrows(
                AuthorizationException.class,
                () -> service.update(mockUser, Mockito.mock(User.class)));
    }

    @Test
    public void update_Should_ThrowException_When_Cannot_Change_Username() {
        User mockUserForUpdate = createMockUser();

        User mockUser = createMockUser();
        mockUser.setUsername("TestUsername");

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.update(mockUser, mockUserForUpdate));
    }


    @Test
    void getUserById_Should_CallRepository() {





        service.get(Mockito.anyInt());

        Mockito.verify(mockRepository, Mockito.times(1))
                .get(Mockito.anyInt());
    }
    @Test
    public void update_ShouldUpdateUserByAdmin() {
        User adminUser = Helpers.createMockAdmin();
        User mockUser = Helpers.createMockUser();


        assertDoesNotThrow(() -> service.update(adminUser, mockUser));
        verify(mockRepository, times(1)).update(Mockito.any(User.class));
    }

    @Test
    public void update_ShouldUpdateUserByCreator() {
        //User adminUser = Helpers.createMockAdmin();
        User mockUser = Helpers.createMockUser();


        assertDoesNotThrow(() -> service.update(mockUser, mockUser));
        verify(mockRepository, times(1)).update(Mockito.any(User.class));
    }

    @Test
    public void update_ShouldThrowAuthorizationException() {
        User mockUser = Helpers.createMockUser();
        User unauthorizedUser = Helpers.createMockUser();
        unauthorizedUser.setId(123);
        unauthorizedUser.setUsername("asd");

        assertThrows(AuthorizationException.class, () -> service.update(unauthorizedUser, mockUser));
    }
    @Test
    public void update_ShouldThrowIllegalArgumentException() {
        User mockUser = Helpers.createMockUser();
        User invalidUser = Helpers.createMockUser();
        invalidUser.setId(123);
        invalidUser.setUsername("asd");

        assertThrows(AuthorizationException.class, () -> service.update(invalidUser, mockUser));
    }
}
