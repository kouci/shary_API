package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.User;
import fr.ynov.shary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

   // @Test
    /*void createUser() {
        // Given
        User user = new User();
        user.setId(1L);
        when(userRepository.save(any())).thenReturn(user);

        // When
        long userId = userService.createUser(user);

        // Then
        assertEquals(1L, userId);
        verify(userRepository, times(1)).save(any());
    }*/

    @Test
    void deleteUser() {
        // Given
        long userId = 1L;

        // When
        userService.deleteUser(userId);

        // Then
        verify(userRepository, times(1)).deleteById(userId);
    }

    /*@Test
    void updateUser() {
        // Given
        User user = new User();
        user.setId(1L);
        when(userRepository.save(any())).thenReturn(user);

        // When
        long userId = userService.updateUser(user);

        // Then
        assertEquals(1L, userId);
        verify(userRepository, times(1)).save(any());
    }*/

    @Test
    void getUser() {
        // Given
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When


        User retrievedUser = userService.getUser(userId);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void getUserByUsername() {
        // Given
        String username = "testUser";
        User user = new User();
        user.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When
        User retrievedUser = userService.getUserByUsername(username);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(username, retrievedUser.getUsername());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void getUserByEmail() {
        // Given
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(user);

        // When
        User retrievedUser = userService.getUserByEmail(email);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(email, retrievedUser.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> retrievedUsers = userService.getAllUsers();

        // Then
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
        verify(userRepository, times(1)).findAll();
    }
}
