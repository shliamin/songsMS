package songsMS.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(null, "John", "Doe", null, null));
        userList.add(new User(null, "Alice", "Smith", null, null));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userController.getAllUsers();

        assertEquals(userList, result);
    }
    
    @Test
    public void testGetUserByNickname() {
        User testUser = new User(null, "John", "Doe", null, null);

        when(userRepository.findByNickname("john_doe")).thenReturn(Optional.of(testUser));

        User result = userController.getUserByNickname("john_doe");

        assertEquals(testUser, result);
    }

    
    @Test
    public void testCreateUser() {
        User testUser = new User(null, "Alice", "Smith", null, null);

        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userController.createUser(testUser);

        assertEquals(testUser, result);
    }

    
    @Test
    public void testUpdateUser() {
        User existingUser = new User(null, "John", "Doe", null, null);
        User updatedUser = new User(null, "Alice", "Smith", null, null);

        when(userRepository.findByNickname("john_doe")).thenReturn(Optional.of(existingUser));
        
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        User result = userController.updateUser("john_doe", updatedUser);

        assertEquals(updatedUser, result);
    }

    
    @Test
    public void testDeleteUser() {
        userController.deleteUser("john_doe");

        verify(userRepository).deleteByNickname("john_doe");
    }

}

