package pl.edu.pg.eti.kask.wingspan.datastore.component;

import lombok.extern.java.Log;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
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
public class DataStore {

    /**
     * Set of all available actions.
     */
    private final Set<Action> actions = new HashSet<>();

    /**
     * Set of all birds.
     */
    private final Set<Bird> birds = new HashSet<>();

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
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    /**
     * Seeks for all actions.
     *
     * @return list (can be empty) of all actions
     */
    public synchronized List<Action> findAllActions() {
        return actions.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Stores new action.
     *
     * @param value new action to be stored
     * @throws IllegalArgumentException if action with provided id already exists
     */
    public synchronized void createAction(Action value) throws IllegalArgumentException {
        if (actions.stream().anyMatch(action -> action.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The action id \"%s\" is not unique".formatted(value.getId()));
        }
        actions.add(cloningUtility.clone(value));
    }

    /**
     * Seeks for all birds.
     *
     * @return list (can be empty) of all birds
     */
    public synchronized List<Bird> findAllBirds() {
        return birds.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Stores new bird.
     *
     * @param value new bird to be stored
     * @throws IllegalArgumentException if bird with provided id already exists or when {@link User} or
     *                                  {@link Action} with provided uuid does not exist
     */
    public synchronized void createBird(Bird value) throws IllegalArgumentException {
        if (birds.stream().anyMatch(bird -> bird.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The bird id \"%s\" is not unique".formatted(value.getId()));
        }
        Bird entity = cloneWithRelationships(value);
        birds.add(entity);
    }

    /**
     * Updates existing bird.
     *
     * @param value bird to be updated
     * @throws IllegalArgumentException if bird with the same id does not exist or when {@link User} or
     *                                  {@link Action} with provided uuid does not exist
     */
    public synchronized void updateBird(Bird value) throws IllegalArgumentException {
        Bird entity = cloneWithRelationships(value);
        if (birds.removeIf(bird -> bird.getId().equals(value.getId()))) {
            birds.add(entity);
        } else {
            throw new IllegalArgumentException("The bird with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    /**
     * Deletes existing bird.
     *
     * @param id id of bird to be deleted
     * @throws IllegalArgumentException if bird with provided id does not exist
     */
    public synchronized void deleteBird(UUID id) throws IllegalArgumentException {
        if (!birds.removeIf(bird -> bird.getId().equals(id))) {
            throw new IllegalArgumentException("The bird with id \"%s\" does not exist".formatted(id));
        }
    }

    /**
     * Seeks for all users.
     *
     * @return list (can be empty) of all users
     */
    public synchronized List<User> findAllUsers() {
        return users.stream().map(cloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Stores new user.
     *
     * @param value new user to be stored
     * @throws IllegalArgumentException if user with provided id already exists
     */
    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(bird -> bird.getId().equals(value.getId()))) {
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
        if (users.removeIf(bird -> bird.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    public synchronized void deleteUser(User entity) throws IllegalArgumentException {
    if (!users.removeIf(user -> user.getId().equals(entity.getId()))) {
        throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(entity.getId()));
    }
}

    /**
     * Clones existing bird and updates relationships for values in storage
     *
     * @param value bird
     * @return cloned value with updated relationships
     * @throws IllegalArgumentException when {@link User} or {@link Action} with provided uuid does not exist
     */
    private Bird cloneWithRelationships(Bird value) {
        Bird entity = cloningUtility.clone(value);

        if (entity.getUser() != null) {
            entity.setUser(users.stream().filter(user -> user.getId().equals(value.getUser().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("No user with id \"%s\".".formatted(value.getUser().getId()))));
        }

        if (entity.getAction() != null) {
            entity.setAction(actions.stream().filter(action -> action.getId().equals(value.getAction().getId())).findFirst().orElseThrow(() -> new IllegalArgumentException("No action with id \"%s\".".formatted(value.getAction().getId()))));
        }

        return entity;
    }

}
