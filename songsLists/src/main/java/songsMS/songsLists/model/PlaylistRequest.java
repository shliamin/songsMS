package songsMS.songsLists.model;

import java.util.List;
import java.util.Objects;


public class PlaylistRequest {
    private boolean isPrivate;
    private User user;
    private String name;
    private List<Song> songList;
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Song> getSongList() {
		return songList;
	}
	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(isPrivate, name, songList, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistRequest other = (PlaylistRequest) obj;
		return isPrivate == other.isPrivate && Objects.equals(name, other.name)
				&& Objects.equals(songList, other.songList) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "PlaylistRequest [isPrivate=" + isPrivate + ", user=" + user + ", name=" + name + ", songList="
				+ songList + "]";
	}
	public PlaylistRequest(boolean isPrivate, User user, String name, List<Song> songList) {
		super();
		this.isPrivate = isPrivate;
		this.user = user;
		this.name = name;
		this.songList = songList;
	}
    
    public PlaylistRequest() {
    	
    }
}