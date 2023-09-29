package songsMS.songsLists.model;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "playlists")
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

	public User getUser() {
		// TODO Auto-generated method stub
		return user;
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

	public List<Song> getSongs() {
		// TODO Auto-generated method stub
		return songs;
	}

	public Playlist(int id, User user, String name, boolean isPrivate, List<Song> songs) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.isPrivate = isPrivate;
		this.songs = songs;
	}
	
	public Playlist() {
		
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", user=" + user + ", name=" + name + ", isPrivate=" + isPrivate + ", songs="
				+ songs + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isPrivate, name, songs, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Playlist other = (Playlist) obj;
		return id == other.id && isPrivate == other.isPrivate && Objects.equals(name, other.name)
				&& Objects.equals(songs, other.songs) && Objects.equals(user, other.user);
	}
	
	
}