package pl.edu.pg.eti.kask.wingspan.bird.repository.api;

import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.repository.api.Repository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for bird entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface BirdRepository extends Repository<Bird, UUID> {

    /**
     * Seeks for single user's bird.
     *
     * @param id   bird's id
     * @param user bird's owner
     * @return container (can be empty) with bird
     */
    Optional<Bird> findByIdAndUser(UUID id, User user);

    /**
     * Seeks for all user's birds.
     *
     * @param user birds' owner
     * @return list (can be empty) of user's birds
     */
    List<Bird> findAllByUser(User user);

    /**
     * Seeks for all action's birds.
     *
     * @param action bird's action
     * @return list (can be empty) of user's birds
     */
    List<Bird> findAllByAction(Action action);

}

