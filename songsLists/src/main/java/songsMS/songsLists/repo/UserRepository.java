package songsMS.songsLists.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import songsMS.songsLists.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);
    User findByUserIdAndPassword(String userId, String password);

    boolean existsByUserId(String userId);
}