package pl.edu.pg.eti.kask.wingspan.bird.repository.memory;

import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.BirdRepository;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Repository for bird entity. Repositories should be used in business layer (e.g.: in services).
 */
public class BirdInMemoryRepository implements BirdRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    public BirdInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Bird> find(UUID id) {
        return store.findAllBirds().stream()
                .filter(bird -> bird.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Bird> findAll() {
        return store.findAllBirds();
    }

    @Override
    public void create(Bird entity) {
        store.createBird(entity);
    }

    @Override
    public void delete(Bird entity) {
        store.deleteBird(entity.getId());
    }

    @Override
    public void update(Bird entity) {
        store.updateBird(entity);
    }

    @Override
    public Optional<Bird> findByIdAndUser(UUID id, User user) {
        return store.findAllBirds().stream()
                .filter(bird -> bird.getUser().equals(user))
                .filter(bird -> bird.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Bird> findAllByUser(User user) {
        return store.findAllBirds().stream()
                .filter(bird -> user.equals(bird.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bird> findAllByAction(Action action) {
        return store.findAllBirds().stream()
                .filter(bird -> action.equals(bird.getAction()))
                .collect(Collectors.toList());
    }

}

