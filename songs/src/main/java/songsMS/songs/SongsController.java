package songsMS.songs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongsController {

    private final SongsRepository songsRepository;

    @Autowired
    public SongsController(SongsRepository songsRepository) {
        this.songsRepository = songsRepository;
    }

    // Endpoint to get all songs
    @GetMapping
    public List<Songs> getAllSongs() {
        return songsRepository.findAll();
    }

    // Endpoint to get a song by ID
    @GetMapping("/{id}")
    public Songs getSongById(@PathVariable Long id) {
        return songsRepository.findById(id).orElse(null);
    }

    // Endpoint to create a new song
    @PostMapping
    public Songs createSong(@RequestBody Songs song) {
        return songsRepository.save(song);
    }

    // Endpoint to update a song by ID
    @PutMapping("/{id}")
    public Songs updateSong(@PathVariable Long id, @RequestBody Songs updatedSong) {
        Songs existingSong = songsRepository.findById(id).orElse(null);
        if (existingSong != null) {
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setArtist(updatedSong.getArtist());
            // Set other properties as needed
            return songsRepository.save(existingSong);
        }
        return null; // Handle not found case
    }

    // Endpoint to delete a song by ID
    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        songsRepository.deleteById(id);
    }
}
