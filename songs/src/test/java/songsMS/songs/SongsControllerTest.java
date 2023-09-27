package songsMS.songs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SongsControllerTest {

    @InjectMocks
    private SongsController controller;

    @Mock
    private SongsRepository songsRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSongs() {
        // Mock the songsRepository to return a list of songs
        List<Songs> songsList = new ArrayList<>();
        songsList.add(new Songs(1L, "Song 1", "Artist 1", null, null));
        songsList.add(new Songs(2L, "Song 2", "Artist 2", null, null));
        when(songsRepository.findAll()).thenReturn(songsList);

        // Call the controller method
        List<Songs> result = controller.getAllSongs();

        // Verify that the response matches the expected list of songs
        assertEquals(songsList, result);
    }

    @Test
    public void testGetSongById() {
        // Mock the songsRepository to return a song with a specific ID
        Long songId = 1L;
        Songs expectedSong = new Songs(songId, "Song 1", "Artist 1", null, null);
        when(songsRepository.findById(songId)).thenReturn(Optional.of(expectedSong));

        // Call the controller method
        Songs result = controller.getSongById(songId);

        // Verify that the response matches the expected song
        assertEquals(expectedSong, result);
    }

    @Test
    public void testCreateSong() {
        // Mock the songsRepository to save a song and return the saved song
        Songs newSong = new Songs(null, "New Song", "New Artist", null, null);
        Songs savedSong = new Songs(1L, "New Song", "New Artist", null, null);
        when(songsRepository.save(newSong)).thenReturn(savedSong);

        // Call the controller method
        Songs result = controller.createSong(newSong);

        // Verify that the response matches the saved song
        assertEquals(savedSong, result);
    }

    @Test
    public void testUpdateSong() {
        // Mock the songsRepository to return an existing song and save the updated song
        Long songId = 1L;
        Songs existingSong = new Songs(songId, "Song 1", "Artist 1", null, null);
        Songs updatedSong = new Songs(songId, "Updated Song", "Updated Artist", null, null);
        when(songsRepository.findById(songId)).thenReturn(Optional.of(existingSong));
        when(songsRepository.save(existingSong)).thenReturn(updatedSong);

        // Call the controller method
        Songs result = controller.updateSong(songId, updatedSong);

        // Verify that the response matches the updated song
        assertEquals(updatedSong, result);
    }

    @Test
    public void testDeleteSong() {
        // Call the controller method to delete a song
        Long songId = 1L;
        controller.deleteSong(songId);

        // Verify that the deleteById method was called with the correct ID
        verify(songsRepository, times(1)).deleteById(songId);
    }
}
