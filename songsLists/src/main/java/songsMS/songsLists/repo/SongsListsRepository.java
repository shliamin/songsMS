package songsMS.songsLists.repo;

import songsMS.songsLists.model.Playlist;
import songsMS.songsLists.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongsListsRepository extends CrudRepository<Playlist, Long> {
    // You can define additional custom query methods if needed
    List<Playlist> findAllByUser(User user);

    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    List<Playlist> findAllByUserIdAndIsPrivateFalse(long userId);
}