package songsMS.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoint to get all songs
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Endpoint to get a user by nickname
    @GetMapping("/{nickname}")
    public User getUserByNickname(@PathVariable String nickname) {
        return userRepository.findByNickname(nickname).orElse(null);
    }

    // Endpoint to create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Endpoint to update a user by nickname
    @PutMapping("/{nickname}")
    public User updateUser(@PathVariable String nickname, @RequestBody User updatedUser) {
        User existingUser = userRepository.findByNickname(nickname).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            // Set other properties as needed
            return userRepository.save(existingUser);
        }
        return null; // Handle not found case
    }

    // Endpoint to delete a user by nickname
    @DeleteMapping("/{nickname}")
    public void deleteUser(@PathVariable String nickname) {
        userRepository.deleteByNickname(nickname);
    }
}
