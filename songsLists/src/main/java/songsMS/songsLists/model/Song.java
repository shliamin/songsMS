package songsMS.songsLists.model;

import lombok.*;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
@Builder
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String artist;

    @Column
    private String label;

    @Column
    private int released;

    public Song(String title, String artist, String label, int released) {
        this.title = title;
        this.artist = artist;
        this.label = label;
        this.released = released;
    }
    
    public Song() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getReleased() {
		return released;
	}

	public void setReleased(int released) {
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
		return Objects.equals(artist, other.artist) && id == other.id && Objects.equals(label, other.label)
				&& released == other.released && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", title=" + title + ", artist=" + artist + ", label=" + label + ", released="
				+ released + "]";
	}
	
	
    
    


}