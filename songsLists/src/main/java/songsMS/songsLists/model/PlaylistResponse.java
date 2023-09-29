package songsMS.songsLists.model;


import java.util.List;
import java.util.Objects;


public class PlaylistResponse {

    private Long id;
    private boolean isPrivate;
    private Long ownerId;
    private String name;
    private List<Song> songList;

    

    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public boolean isPrivate() {
		return isPrivate;
	}



	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}



	public long getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(Long long1) {
		this.ownerId = long1;
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
		return Objects.hash(id, isPrivate, name, ownerId, songList);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistResponse other = (PlaylistResponse) obj;
		return Objects.equals(id, other.id) && isPrivate == other.isPrivate && Objects.equals(name, other.name)
				&& Objects.equals(ownerId, other.ownerId) && Objects.equals(songList, other.songList);
	}
	
	





	@Override
	public String toString() {
		return "PlaylistResponse [id=" + id + ", isPrivate=" + isPrivate + ", ownerId=" + ownerId + ", name=" + name
				+ ", songList=" + songList + "]";
	}
	
	







	public PlaylistResponse(Long id, boolean isPrivate, Long ownerId, String name, List<Song> songList) {
		super();
		this.id = id;
		this.isPrivate = isPrivate;
		this.ownerId = ownerId;
		this.name = name;
		this.songList = songList;
	}









	public PlaylistResponse() {
		
	}









	
    public static class Song {
        private Long id;
        private String title;
        private String artist;
        private String label;
        private Integer released;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getArtist() {
			return artist;
		}
		public void setArtist(String artist) {
			this.artist = artist;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public Integer getReleased() {
			return released;
		}
		public void setReleased(Integer released) {
			this.released = released;
		}
		@Override
		public int hashCode() {
			return Objects.hash(artist, id, label, released, title);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Song other = (Song) obj;
			return Objects.equals(artist, other.artist) && Objects.equals(id, other.id)
					&& Objects.equals(label, other.label) && Objects.equals(released, other.released)
					&& Objects.equals(title, other.title);
		}
		@Override
		public String toString() {
			return "Song [id=" + id + ", title=" + title + ", artist=" + artist + ", label=" + label + ", released="
					+ released + "]";
		}
		public Song(Long id, String title, String artist, String label, Integer released) {
			super();
			this.id = id;
			this.title = title;
			this.artist = artist;
			this.label = label;
			this.released = released;
		}
		public Song() {
	
		}

      
    }
}
