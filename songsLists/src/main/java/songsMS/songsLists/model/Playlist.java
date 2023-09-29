package songsMS.songsLists.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "playlists")
@NoArgsConstructor
public class Playlist {

    public Playlist(PlaylistRequest playlistRequest) {
        setUser(playlistRequest.getUser());
        setPrivate(playlistRequest.isPrivate());
        setSongs(playlistRequest.getSongList());
        setName(playlistRequest.getName());
    }

    public void setName(String name2) {
		// TODO Auto-generated method stub
		
	}

	public void setSongs(List<Song> songList) {
		// TODO Auto-generated method stub
		
	}

	public void setPrivate(boolean private1) {
		// TODO Auto-generated method stub
		
	}

	public void setUser(User user2) {
		// TODO Auto-generated method stub
		
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    @Column(name = "is_private")
    private boolean isPrivate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "playlist_song_mapping",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs = new ArrayList<>();

	public Auth getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isPrivate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Song[] getSongs() {
		// TODO Auto-generated method stub
		return null;
	}
}