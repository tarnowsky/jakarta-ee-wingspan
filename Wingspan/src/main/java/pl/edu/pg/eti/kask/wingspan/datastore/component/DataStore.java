package pl.edu.pg.eti.kask.wingspan.datastore.component;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.serialization.component.CloningUtility;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * For the sake of simplification instead of using real database this example is using a data source object which should
 * be put in servlet context in a single instance. In order to avoid {@link java.util.ConcurrentModificationException}
 * all methods are synchronized. Normally synchronization would be carried on by the database server. Caution, this is
 * very inefficient implementation but can be used to present other mechanisms without obscuration example with ORM
 * usage.
 */
@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {

    /**
     * Set of all available genres.
     */
    private final Set<Genre> genres = new HashSet<>();

    /**
     * Set of all musicians.
     */
    private final Set<Musician> musicians = new HashSet<>();

    /**
     * Set of all users.
     */
    private final Set<User> users = new HashSet<>();

    /**
     * Component used for creating deep copies.
     */
    private final CloningUtility cloningUtility;

    /**
     * @param cloningUtility component used for creating deep copies
     */
    @Inject
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    /**
     * Seeks for all genres.
     *
     * @return list (can be empty) of all genres
     */
    public synchronized List<Genre> findAllGenres() {
        return genres.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new genre.
     *
     * @param value new genre to be stored
     * @throws IllegalArgumentException if genre with provided id already exists
     */
    public synchronized void createGenre(Genre value) throws IllegalArgumentException {
        if (genres.stream().anyMatch(genre -> genre.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The genre id \"%s\" is not unique".formatted(value.getId()));
        }
        genres.add(cloningUtility.clone(value));
    }

    /**
     * Seeks for all musicians.
     *
     * @return list (can be empty) of all musicians
     */
    public synchronized List<Musician> findAllMusicians() {
        return musicians.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new musician.
     *
     * @param value new musician to be stored
     * @throws IllegalArgumentException if musician with provided id already exists or when {@link User} or
     *                                  {@link Genre} with provided uuid does not exist
     */
    public synchronized void createMusician(Musician value) throws IllegalArgumentException {
        if (musicians.stream().anyMatch(musician -> musician.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The musician id \"%s\" is not unique".formatted(value.getId()));
        }
        Musician entity = cloneWithRelationships(value);
        musicians.add(entity);
    }

    /**
     * Updates existing musician.
     *
     * @param value musician to be updated
     * @throws IllegalArgumentException if musician with the same id does not exist or when {@link User} or
     *                                  {@link Genre} with provided uuid does not exist
     */
    public synchronized void updateMusician(Musician value) throws IllegalArgumentException {
        Musician entity = cloneWithRelationships(value);
        if (musicians.removeIf(musician -> musician.getId().equals(value.getId()))) {
            musicians.add(entity);
        } else {
            throw new IllegalArgumentException("The musician with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Deletes existing musician.
     *
     * @param id id of musician to be deleted
     * @throws IllegalArgumentException if musician with provided id does not exist
     */
    public synchronized void deleteMusician(UUID id) throws IllegalArgumentException {
        if (!musicians.removeIf(musician -> musician.getId().equals(id))) {
            throw new IllegalArgumentException("The musician with id \"%s\" does not exist".formatted(id));
        }
    }

    /**
     * Seeks for all users.
     *
     * @return list (can be empty) of all users
     */
    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new user.
     *
     * @param value new user to be stored
     * @throws IllegalArgumentException if user with provided id already exists
     */
    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(musician -> musician.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    /**
     * Updates existing user.
     *
     * @param value user to be updated
     * @throws IllegalArgumentException if user with the same id does not exist
     */
    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(musician -> musician.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Clones existing musician and updates relationships for values in storage
     *
     * @param value musician
     * @return cloned value with updated relationships
     * @throws IllegalArgumentException when {@link User} or {@link Genre} with provided uuid does not exist
     */
    private Musician cloneWithRelationships(Musician value) {
        Musician entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream()
                    .filter(user -> user.getId().equals(value.getUser().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getGenre() != null) {
            entity.setGenre(genres.stream()
                    .filter(genre -> genre.getId().equals(value.getGenre().getId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No genre with id \"%s\".".formatted(value.getGenre().getId()))));
        }

        return entity;
    }

}
