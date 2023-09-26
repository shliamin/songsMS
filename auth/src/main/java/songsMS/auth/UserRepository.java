package songsMS.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public interface UserRepository extends JpaRepository<User, Long> {
	  @Override
	  List<User> findAll();

	Optional<User> findByNickname(String nickname);

	void deleteByNickname(String nickname);	
}


