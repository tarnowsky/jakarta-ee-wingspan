package pl.edu.pg.eti.kask.wingspan.bird.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.ActionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding bird's action entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class ActionService {

    /**
     * Repository for action entity.
     */
    private final ActionRepository repository;

    /**
     * @param repository repository for action entity
     */
    @Inject
    public ActionService(ActionRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id action's id
     * @return container with action entity
     */
    public Optional<Action> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available actions
     */
    public List<Action> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new action in the data store.
     *
     * @param action new action to be saved
     */
    public void create(Action action) {
        repository.create(action);
    }

}

