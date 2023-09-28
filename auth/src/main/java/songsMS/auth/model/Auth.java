package songsMS.auth.model;


import java.util.Objects;
import jakarta.persistence.*;


@Entity
@Table(name = "auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String token;
    
    public Auth(Long id, User user, String token) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
	}

	public Auth() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Auth [id=" + id + ", user=" + user + ", token=" + token + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, token, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auth other = (Auth) obj;
		return Objects.equals(id, other.id) && Objects.equals(token, other.token) && Objects.equals(user, other.user);
	}
    
    
}