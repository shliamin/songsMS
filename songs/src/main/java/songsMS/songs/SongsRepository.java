package songsMS.songs;

import java.util.List;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public interface SongsRepository extends JpaRepository<Songs, Long> {
	  @Override
	  List<Songs> findAll();	
}


