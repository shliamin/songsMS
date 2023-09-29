package songsMS.songsLists.controller;

import songsMS.songsLists.exception.UnauthorizedException;
import songsMS.songsLists.model.Song;
import songsMS.songsLists.repo.AuthRepository;
import songsMS.songsLists.repo.SongRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import songsMS.songsLists.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/songs")
public class SongsController {

    private final SongRepository songRepo;

    private final AuthRepository authRepo;

    public SongsController(SongRepository songRepo, AuthRepository authRepo) {
        this.songRepo = songRepo;
        this.authRepo = authRepo;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Song> getAllSongs() {
        return songRepo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Song createSong(@RequestBody Song song) {
        return songRepo.save(song);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Song getSongById(@PathVariable(value = "id") Long id) {
        return songRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 üblich
    public Song updateSong(@PathVariable(value = "id") Long id,
                           @RequestBody Song songToPut) {
        Song song = songRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));

        song.setArtist(songToPut.getArtist());
        song.setTitle(songToPut.getTitle());
        song.setLabel(songToPut.getLabel());
        song.setReleased(songToPut.getReleased());
        return songRepo.save(song);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 üblich, Antwort ohne Inhalt
    public ResponseEntity<?> deleteSong(@PathVariable(value = "id") Long id) {
        Song song = songRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
        songRepo.delete(song);
        return ResponseEntity.noContent().build();
    }

    @ModelAttribute // Überprüft bei jeder anfrage
    public void checkAuthorization(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new ResourceNotFoundException("Token", "", token);
        }
        if(!isTokenValid(token)){
            throw new UnauthorizedException("Authorized User not found");
        }
    }

    private boolean isTokenValid(String token){
        return authRepo.existsAuthByToken(token);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}