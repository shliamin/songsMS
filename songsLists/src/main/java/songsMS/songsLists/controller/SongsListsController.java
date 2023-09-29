package songsMS.songsLists.controller;

import songsMS.songsLists.exception.ResourceNotFoundException;
import songsMS.songsLists.exception.UnauthorizedException;
import songsMS.songsLists.model.*;
import songsMS.songsLists.repo.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/songsLists")
public class SongsListsController {

    private final SongsListsRepository playlistRepository;
    private final AuthRepository authRepository;

    private final SongRepository songRepository;
    private final SongsListsMappingRepository mappingRepository;
    private final UserRepository userRepository;


    @Autowired
    public SongsListsController(SongsListsRepository playlistRepository,
                              AuthRepository authRepository,
                              SongRepository songRepository,
                              SongsListsMappingRepository mappingRepository,
                              UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.authRepository = authRepository;
        this.songRepository = songRepository;
        this.mappingRepository = mappingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylistById(@PathVariable Long id,
                                             @RequestHeader("Authorization") String token) {

        Playlist playlist = playlistRepository.findById(id).orElse(null);
        if (playlist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Auth auth = authRepository.findByToken(token);
        if (playlist.getUser().getId() != (auth.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        PlaylistResponse playlistResponse = convertToPlaylistResponse(playlist);
        return ResponseEntity.ok(playlistResponse);
    }


    @GetMapping
    // http://localhost:8080/playlists?userId=maxime
    public ResponseEntity<?> getPlaylistsByUserId(@RequestParam String userId,
                                                  @RequestHeader("Authorization") String token) {
        if(!userRepository.existsByUserId(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Auth auth = authRepository.findByToken(token);
        User user = userRepository.findByUserId(userId);
        System.out.println(">>> " + auth.getUser().getUserId() + " wants Playlists from " + userId+".");

        // Public Playlists of other User
        if (!auth.getUser().getUserId().equals(userId)) {
            List<Playlist> publicPlaylists = playlistRepository.findAllByUserIdAndIsPrivateFalse(user.getId());
            List<PlaylistResponse> playlistResponses = new ArrayList<>();
            for (Playlist playlist : publicPlaylists) {
                playlistResponses.add(convertToPlaylistResponse(playlist));
            }
            return ResponseEntity.ok(playlistResponses);        }


        List<Playlist> playlists = playlistRepository.findAllByUser(auth.getUser());
        List<PlaylistResponse> playlistResponses = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistResponses.add(convertToPlaylistResponse(playlist));
        }
        return ResponseEntity.ok(playlistResponses);
    }

    @PostMapping
    public ResponseEntity<String> createPlaylist(@RequestBody PlaylistRequest playlistRequest,
                                         @RequestHeader("Authorization") String token) {

        Playlist playlist = new Playlist(playlistRequest);
        Auth auth = authRepository.findByToken(token);
        playlist.setUser(auth.getUser());
        Playlist savedPlaylist = playlistRepository.save(playlist);

        for (Song song : playlistRequest.getSongList()){
            if (songRepository.existsById((long) song.getId())){
                mappingRepository.save(new SongsListsMapping((long) savedPlaylist.getId(), (long) song.getId()));
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String token) {

        if (!playlistRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Auth auth = authRepository.findByToken(token);
        Playlist playlist = playlistRepository.findById(id).orElse(null);

        if (playlist == null || playlist.getUser().getId() != (auth.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        playlistRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
        return authRepository.existsAuthByToken(token);
    }

    private PlaylistResponse convertToPlaylistResponse(Playlist playlist) {
        PlaylistResponse playlistResponse = new PlaylistResponse();
        playlistResponse.setId((long) playlist.getId());
        playlistResponse.setPrivate(playlist.isPrivate());
        playlistResponse.setOwnerId(playlist.getUser().getId()); // ownerId ist die userid des Besitzers
        playlistResponse.setName(playlist.getName());

        List<PlaylistResponse.Song> songList = new ArrayList<>();
        for (Song song : playlist.getSongs()) {
            PlaylistResponse.Song songResponse = new PlaylistResponse.Song();
            songResponse.setId((long) song.getId());
            songResponse.setTitle(song.getTitle());
            songResponse.setArtist(song.getArtist());
            songResponse.setLabel(song.getLabel());
            songResponse.setReleased(song.getReleased());

            songList.add(songResponse);
        }
        playlistResponse.setSongList(songList);

        return playlistResponse;
    }

}