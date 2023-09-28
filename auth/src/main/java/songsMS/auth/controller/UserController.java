package songsMS.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import songsMS.auth.exception.ResourceNotFoundException;
import songsMS.auth.model.User;
import songsMS.auth.repo.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRepository userRepo;
    
    public UserController (UserRepository repo) {
        this.userRepo = repo;
    }

    @GetMapping
    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 üblich
    public User updateUser(@PathVariable(value = "id") Long id,
                           @RequestBody User userToPut) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        user.setPassword(userToPut.getPassword());
        user.setFirstName(userToPut.getFirstName());
        user.setLastName(userToPut.getLastName());
        user.setUserId(userToPut.getUserId());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // üblich 204 zurückzugeben
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepo.delete(user);
        return ResponseEntity.noContent().build();
    }
}