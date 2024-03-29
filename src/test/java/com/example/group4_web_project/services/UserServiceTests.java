package com.example.group4_web_project.services;

import com.example.group4_web_project.Helpers;
import com.example.group4_web_project.exceptions.AuthorizationException;
import com.example.group4_web_project.exceptions.EntityDuplicateException;
import com.example.group4_web_project.exceptions.EntityNotFoundException;
import com.example.group4_web_project.models.Comment;
import com.example.group4_web_project.models.FilterOptions;
import com.example.group4_web_project.models.FilterOptionsUser;
import com.example.group4_web_project.models.User;
import com.example.group4_web_project.repositories.AdminPhoneNumberRepository;
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

    @Test
    public void getUserCount_Should_Return_CountOfUsers() {
        // Arrange
        Mockito.when(mockRepository.getUserCount())
                .thenReturn(10);

        // Act
        int userCount = service.getUserCount();

        // Assert
        Assertions.assertEquals(10, userCount);
        Mockito.verify(mockRepository, Mockito.times(1))
                .getUserCount();
    }

    @Test
    public void get_Should_Return_UserWithUsername() {
        // Arrange
        String username = "testUser";
        User mockUser = createMockUser();
        Mockito.when(mockRepository.get(username))
                .thenReturn(mockUser);

        // Act
        User retrievedUser = service.get(username);

        // Assert
        Assertions.assertEquals(mockUser, retrievedUser);
        Mockito.verify(mockRepository, Mockito.times(1))
                .get(username);
    }

    @Test
    public void blockUser_Should_BlockUser() {
        // Arrange
        User mockUser = createMockUser();

        // Act
        service.blockUser(mockUser);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1)).update(mockUser);
        Assertions.assertTrue(mockUser.isBlocked());
    }

    @Test
    public void unblockUser_Should_UnblockUser() {
        // Arrange
        User mockUser = createMockUser();
        mockUser.setBlocked(true);

        // Act
        service.unblockUser(mockUser);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1)).update(mockUser);
        Assertions.assertFalse(mockUser.isBlocked());
    }

    @Test
    public void register_ShouldThrowEntityDuplicateException() {
        User mockUser = Helpers.createMockUser();

        Mockito.when(mockRepository.get(mockUser.getUsername()))
                .thenReturn(mockUser);

        assertThrows(EntityDuplicateException.class, () -> service.register(mockUser));
    }

    @Test
    void register_Should_CallRepository() {

        User user = Helpers.createMockUser();
        Mockito.when(mockRepository.get(user.getUsername()))
                .thenThrow(EntityNotFoundException.class);
        service.register(user);

        Mockito.verify(mockRepository, Mockito.times(1)).register(user);
    }

    @Test
    public void makeUserAdmin_ShouldThrowAuthorizationException() {
        User mockUser = Helpers.createMockUser();
        User mockAdmin = Helpers.createMockUser();

        assertThrows(AuthorizationException.class, () -> service.makeUserAdmin(mockAdmin,mockUser.getId()," "));
    }

    @Test
    public void makeUserAdmin_ShouldThrowEntityDuplicateException() {
        User mockUser = Helpers.createMockAdmin();
        User mockAdmin = Helpers.createMockAdmin();

        Mockito.when(mockRepository.get(mockUser.getId()))
                .thenReturn(mockUser);

        assertThrows(EntityDuplicateException.class, () -> service.makeUserAdmin(mockAdmin,mockUser.getId()," "));
    }

    @Test
    void makeUserAdmin_Should_CallRepository() {

        User mockUser = Helpers.createMockUser();
        User mockAdmin = Helpers.createMockAdmin();

        Mockito.when(mockRepository.get(mockUser.getId()))
                .thenReturn(mockUser);

        service.makeUserAdmin(mockAdmin,mockUser.getId(),null);

        Mockito.verify(mockRepository, Mockito.times(1)).update(mockUser);
    }

    @Test
    void makeUserAdmin_Should_CallRepository_When_AdminHavePhoneNumber() {

        User mockUser = Helpers.createMockUser();
        User mockAdmin = Helpers.createMockAdmin();
        String phoneNumber = "1234567";

        Mockito.when(mockRepository.get(mockUser.getId()))
                .thenReturn(mockUser);

        service.makeUserAdmin(mockAdmin,mockUser.getId(),phoneNumber);

        Mockito.verify(mockRepository, Mockito.times(1)).update(mockUser);
    }
    @Test
    public void delete_ShouldThrowAuthorizationException() {
        User mockUser = Helpers.createMockUser();
        when(mockRepository.get(mockUser.getId())).thenReturn(mockUser);

        User unauthorizedUser = Helpers.createMockUser();
        unauthorizedUser.setId(123);
        assertThrows(AuthorizationException.class, () -> service.delete(mockUser.getId(), unauthorizedUser));
    }

    @Test
    public void delete_Should_CallRepository() {
        User mockUser = Helpers.createMockUser();

        when(mockRepository.get(mockUser.getId())).thenReturn(mockUser);
        service.delete(mockUser.getId(), mockUser);

        Mockito.verify(mockRepository, Mockito.times(1))
                .delete(mockUser);

    }
}

