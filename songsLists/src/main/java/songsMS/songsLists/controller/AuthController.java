package songsMS.songsLists.controller;


import songsMS.songsLists.exception.UnauthorizedException;
import songsMS.songsLists.model.Auth;
import songsMS.songsLists.model.User;
import songsMS.songsLists.repo.AuthRepository;
import songsMS.songsLists.repo.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final AuthRepository authRepo;
    private final UserRepository userRepo;
	
    public AuthController (AuthRepository authRepo, UserRepository userRepo) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createAuthUser(@RequestBody User user) {
        boolean isValidCredentials = checkCredentials(user.getUserId(), user.getPassword());
        if (isValidCredentials) {
            User existingUser = userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword());
            String authToken = generateAuthorizationToken();
            Auth authUser = new Auth(existingUser.getId(), existingUser, authToken);
            authRepo.save(authUser);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/plain");
            return new ResponseEntity<>(authToken, headers, HttpStatus.OK);
        } else {
            throw new UnauthorizedException("User kann nicht authentifiziert werden");
        }
    }
    
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/plain");
        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.UNAUTHORIZED);
    }

    private boolean checkCredentials(String userId, String password) {
        User user = userRepo.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }

    private String generateAuthorizationToken() {
        return UUID.randomUUID().toString().replace("-","").substring(0,10);
    }


}

