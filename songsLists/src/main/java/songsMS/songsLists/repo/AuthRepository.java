package songsMS.songsLists.repo;

import songsMS.songsLists.model.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<Auth, Long> {
    Boolean existsAuthByToken(String token);
    Auth findByToken(String token);
}