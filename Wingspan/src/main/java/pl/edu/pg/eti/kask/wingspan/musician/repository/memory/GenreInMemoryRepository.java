package pl.edu.pg.eti.kask.wingspan.musician.repository.memory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.GenreRepository;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for genre entity. Repositories should be used in business layer (e.g.: in services).
 */
@RequestScoped
public class GenreInMemoryRepository implements GenreRepository {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private final DataStore store;

    /**
     * @param store data store
     */
    @Inject
    public GenreInMemoryRepository(DataStore store) {
        this.store = store;
    }


    @Override
    public Optional<Genre> find(UUID id) {
        return store.findAllGenres().stream()
                .filter(genre -> genre.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Genre> findAll() {
        return store.findAllGenres();
    }

    @Override
    public void create(Genre entity) {
        store.createGenre(entity);
    }

    @Override
    public void delete(Genre entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public void update(Genre entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

}
