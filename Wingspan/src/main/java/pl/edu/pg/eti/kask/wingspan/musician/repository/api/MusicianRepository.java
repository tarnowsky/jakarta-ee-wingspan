package pl.edu.pg.eti.kask.wingspan.musician.repository.api;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.repository.api.Repository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for musician entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface MusicianRepository extends Repository<Musician, UUID> {

    /**
     * Seeks for single user's musician.
     *
     * @param id   musician's id
     * @param user musician's owner
     * @return container (can be empty) with musician
     */
    Optional<Musician> findByIdAndUser(UUID id, User user);

    /**
     * Seeks for all user's musicians.
     *
     * @param user musicians' owner
     * @return list (can be empty) of user's musicians
     */
    List<Musician> findAllByUser(User user);

    /**
     * Seeks for all genre's musicians.
     *
     * @param genre musician's genre
     * @return list (can be empty) of user's musicians
     */
    List<Musician> findAllByGenre(Genre genre);

}
