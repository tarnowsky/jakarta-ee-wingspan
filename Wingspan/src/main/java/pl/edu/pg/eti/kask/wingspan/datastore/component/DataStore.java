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


@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {

    
    private final Set<Genre> genres = new HashSet<>();

    
    private final Set<Musician> musicians = new HashSet<>();

    
    private final Set<User> users = new HashSet<>();

    
    private final CloningUtility cloningUtility;

    
    @Inject
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    
    public synchronized List<Genre> findAllGenres() {
        return genres.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    
    public synchronized void createGenre(Genre value) throws IllegalArgumentException {
        if (genres.stream().anyMatch(genre -> genre.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The genre id \"%s\" is not unique".formatted(value.getId()));
        }
        genres.add(cloningUtility.clone(value));
    }

    
    public synchronized List<Musician> findAllMusicians() {
        return musicians.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    
    public synchronized void createMusician(Musician value) throws IllegalArgumentException {
        if (musicians.stream().anyMatch(musician -> musician.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The musician id \"%s\" is not unique".formatted(value.getId()));
        }
        Musician entity = cloneWithRelationships(value);
        musicians.add(entity);
    }

    
    public synchronized void updateMusician(Musician value) throws IllegalArgumentException {
        Musician entity = cloneWithRelationships(value);
        if (musicians.removeIf(musician -> musician.getId().equals(value.getId()))) {
            musicians.add(entity);
        } else {
            throw new IllegalArgumentException("The musician with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    
    public synchronized void deleteMusician(UUID id) throws IllegalArgumentException {
        if (!musicians.removeIf(musician -> musician.getId().equals(id))) {
            throw new IllegalArgumentException("The musician with id \"%s\" does not exist".formatted(id));
        }
    }

    
    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    
    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(musician -> musician.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    
    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(musician -> musician.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }

    
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
