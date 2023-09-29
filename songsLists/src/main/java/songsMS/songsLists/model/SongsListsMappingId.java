package songsMS.songsLists.model;


import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SongsListsMappingId implements Serializable {
    private Long playlistId;
    private Long songId;
	public Long getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(Long playlistId) {
		this.playlistId = playlistId;
	}
	public Long getSongId() {
		return songId;
	}
	public void setSongId(Long songId) {
		this.songId = songId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(playlistId, songId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongsListsMappingId other = (SongsListsMappingId) obj;
		return Objects.equals(playlistId, other.playlistId) && Objects.equals(songId, other.songId);
	}
	@Override
	public String toString() {
		return "SongsListsMappingId [playlistId=" + playlistId + ", songId=" + songId + "]";
	}
	public SongsListsMappingId(Long playlistId, Long songId) {
		super();
		this.playlistId = playlistId;
		this.songId = songId;
	}
    
    public SongsListsMappingId() {
    	
    }
}