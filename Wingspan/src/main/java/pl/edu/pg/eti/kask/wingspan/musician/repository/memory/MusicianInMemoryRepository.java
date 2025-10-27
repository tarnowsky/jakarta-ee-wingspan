package pl.edu.pg.eti.kask.wingspan.musician.repository.memory;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.MusicianRepository;
import pl.edu.pg.eti.kask.wingspan.datastore.component.DataStore;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequestScoped
public class MusicianInMemoryRepository implements MusicianRepository {

    
    private final DataStore store;

    
    @Inject
    public MusicianInMemoryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Musician> find(UUID id) {
        return store.findAllMusicians().stream()
                .filter(musician -> musician.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Musician> findAll() {
        return store.findAllMusicians();
    }

    @Override
    public void create(Musician entity) {
        store.createMusician(entity);
    }

    @Override
    public void delete(Musician entity) {
        store.deleteMusician(entity.getId());
    }

    @Override
    public void update(Musician entity) {
        store.updateMusician(entity);
    }

    @Override
    public Optional<Musician> findByIdAndUser(UUID id, User user) {
        return store.findAllMusicians().stream()
                .filter(musician -> musician.getUser().equals(user))
                .filter(musician -> musician.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Musician> findAllByUser(User user) {
        return store.findAllMusicians().stream()
                .filter(musician -> user.equals(musician.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Musician> findAllByGenre(Genre genre) {
        return store.findAllMusicians().stream()
                .filter(musician -> genre.equals(musician.getGenre()))
                .collect(Collectors.toList());
    }

}
