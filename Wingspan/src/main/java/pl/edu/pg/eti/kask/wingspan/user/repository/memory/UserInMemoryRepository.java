package pl.edu.pg.eti.kask.wingspan.user.repository.memory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * In-memory implementation of the UserRepository interface.
 *
 * <p>This implementation uses a DataStore to manage user entities in memory.
 * In future versions, this should be replaced with a database-backed implementation.</p>
 */
@RequestScoped
public class UserInMemoryRepository implements UserRepository {
    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * Constructs a new UserInMemoryRepository with the specified data store.
     *
     * @param store data store for managing user entities
     */
    @Inject
    public UserInMemoryRepository(DataStore store) {
        this.store = store;
    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param id the unique identifier of the user
     * @return an Optional containing the user if found, empty otherwise
     */
    @Override
    public Optional<User> find(UUID id) {
        return store.findAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return a list of all users
     */
    @Override
    public List<User> findAll() {
        return store.findAllUsers();
    }

    /**
     * Creates a new user in the repository.
     *
     * @param entity the user to create
     */
    @Override
    public void create(User entity) {
        store.createUser(entity);
    }

    /**
     * Deletes a user from the repository.
     *
     * @param entity the user to delete
     * @throws UnsupportedOperationException as this operation is not implemented
     */
    @Override
    public void delete(User entity) {
        store.deleteUser(entity);
    }

    /**
     * Updates an existing user in the repository.
     *
     * @param entity the user to update
     */
    @Override
    public void update(User entity) {
        store.updateUser(entity);
    }

    /**
     * Finds a user by their login name.
     *
     * @param login the login name of the user
     * @return an Optional containing the user if found, empty otherwise
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return store.findAllUsers().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }
}
