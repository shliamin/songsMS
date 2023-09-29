package songsMS.songsLists.model;

import java.util.Objects;

import jakarta.persistence.*;


@Entity
@IdClass(SongsListsMappingId.class)
@Table(name = "playlist_song_mapping")
public class SongsListsMapping {

    @Id
    @Column(name = "playlist_id")
    private Long playlistId;

    @Id
    @Column(name = "song_id")
    private Long songId;

    public SongsListsMapping(Long playlistId, Long songId) {
        this.playlistId = playlistId;
        this.songId = songId;
    }
    
    public SongsListsMapping() {
      
    }

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
		SongsListsMapping other = (SongsListsMapping) obj;
		return Objects.equals(playlistId, other.playlistId) && Objects.equals(songId, other.songId);
	}

	@Override
	public String toString() {
		return "SongsListsMapping [playlistId=" + playlistId + ", songId=" + songId + "]";
	}
	
	
	
	

    
}