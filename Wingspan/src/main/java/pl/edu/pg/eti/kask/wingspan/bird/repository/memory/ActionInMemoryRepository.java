package pl.edu.pg.eti.kask.wingspan.bird.repository.memory;

import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.ActionRepository;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for action entity. Repositories should be used in business layer (e.g.: in services).
 */
public class ActionInMemoryRepository implements ActionRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    public ActionInMemoryRepository(DataStore store) {
        this.store = store;
    }


    @Override
    public Optional<Action> find(UUID id) {
        return store.findAllActions().stream()
                .filter(action -> action.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Action> findAll() {
        return store.findAllActions();
    }

    @Override
    public void create(Action entity) {
        store.createAction(entity);
    }

    @Override
    public void delete(Action entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public void update(Action entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

}

