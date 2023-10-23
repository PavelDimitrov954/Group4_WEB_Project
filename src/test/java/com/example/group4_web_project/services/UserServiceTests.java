package com.example.group4_web_project.services;

import com.example.group4_web_project.exceptions.AuthorizationException;
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

}
