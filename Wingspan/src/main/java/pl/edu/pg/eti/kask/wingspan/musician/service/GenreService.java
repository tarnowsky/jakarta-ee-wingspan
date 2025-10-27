package pl.edu.pg.eti.kask.wingspan.musician.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding musician's genre entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class GenreService {

    /**
     * Repository for genre entity.
     */
    private final GenreRepository repository;

    /**
     * @param repository repository for genre entity
     */
    @Inject
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    /**
     * @param id genre's id
     * @return container with genre entity
     */
    public Optional<Genre> find(UUID id) {
        return repository.find(id);
    }

    /**
     * @return all available genres
     */
    public List<Genre> findAll() {
        return repository.findAll();
    }

    /**
     * Stores new genre in the data store.
     *
     * @param genre new genre to be saved
     */
    public void create(Genre genre) {
        repository.create(genre);
    }

}
