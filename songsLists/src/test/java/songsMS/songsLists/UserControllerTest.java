package songsMS.songsLists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import songsMS.songsLists.exception.ResourceNotFoundException;
import songsMS.songsLists.model.User;
import songsMS.songsLists.repo.UserRepository;
import songsMS.songsLists.controller.UserController;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private UserController userController;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> result = userController.getAllUsers();

        assertEquals(users, result);
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userController.getUserById(userId);

        assertEquals(user, result);
    }

    @Test
    public void testGetUserByIdNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userController.getUserById(userId));
    }

    @Test
    public void testCreateUser() {
        User userToCreate = new User();
        when(userRepository.save(userToCreate)).thenReturn(userToCreate);

        User createdUser = userController.createUser(userToCreate);

        assertEquals(userToCreate, createdUser);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User();
        User userToUpdate = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userController.updateUser(userId, userToUpdate);

        assertEquals(existingUser, updatedUser);
    }

    @Test
    public void testUpdateUserNotFound() {
        Long userId = 1L;
        User userToUpdate = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userController.updateUser(userId, userToUpdate));
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        User existingUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        ResponseEntity<?> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userController.deleteUser(userId));
    }
}