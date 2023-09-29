package songsMS.songsLists;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import songsMS.songsLists.controller.AuthController;
import songsMS.songsLists.exception.UnauthorizedException;
import songsMS.songsLists.model.User;
import songsMS.songsLists.repo.AuthRepository;
import songsMS.songsLists.repo.UserRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    @Mock
    private AuthRepository authRepo;

    @Mock
    private UserRepository userRepo;

    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.initMocks(this);
        authController = new AuthController(authRepo, userRepo);
    }

    @Test
    public void testCreateAuthUserUnauthorized() {
        // Create a fake user with invalid credentials
        User fakeUser = new User(0, "invalidUser", "invalidPassword", null, null);

        // Mock the methods
        when(userRepo.findByUserIdAndPassword("invalidUser", "invalidPassword")).thenReturn(null);

        // Check that UnauthorizedException is thrown for invalid credentials
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            authController.createAuthUser(fakeUser);
        });

        // Verify that the response sets the UNAUTHORIZED status for the exception
        ResponseEntity<String> response = authController.handleUnauthorizedException(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

}
