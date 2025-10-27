package pl.edu.pg.eti.kask.wingspan.musician.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class GenreService {

    
    private final GenreRepository repository;

    
    @Inject
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    
    public Optional<Genre> find(UUID id) {
        return repository.find(id);
    }

    
    public List<Genre> findAll() {
        return repository.findAll();
    }

    
    public void create(Genre genre) {
        repository.create(genre);
    }

}
