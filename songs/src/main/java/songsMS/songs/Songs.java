package songsMS.songs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Songs {
	
	@Id
	private Long id;
	private String title;
	private String artist;
	private String label;
	private Integer released;

	public Songs() {
		
	}
	
	public Songs(Long id, String title, String artist, String label, Integer released) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.label = label;
		this.released = released;
	}

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
	
}
