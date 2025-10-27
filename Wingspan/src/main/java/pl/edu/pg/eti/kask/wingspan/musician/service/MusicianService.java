package pl.edu.pg.eti.kask.wingspan.musician.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.MusicianRepository;
import pl.edu.pg.eti.kask.wingspan.musician.repository.api.GenreRepository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class MusicianService {

    
    private final MusicianRepository musicianRepository;

    
    private final GenreRepository genreRepository;

    
    private final UserRepository userRepository;

    
    @Inject
    public MusicianService(MusicianRepository musicianRepository, GenreRepository genreRepository, UserRepository userRepository) {
        this.musicianRepository = musicianRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    
    public Optional<Musician> find(UUID id) {
        return musicianRepository.find(id);
    }

    
    public Optional<Musician> find(User user, UUID id) {
        return musicianRepository.findByIdAndUser(id, user);
    }

    
    public List<Musician> findAll() {
        return musicianRepository.findAll();
    }

    
    public List<Musician> findAll(User user) {
        return musicianRepository.findAllByUser(user);
    }

    
    public void create(Musician musician) {
        musicianRepository.create(musician);
    }

    
    public void update(Musician musician) {
        musicianRepository.update(musician);
    }

    
    public void delete(UUID id) {
        musicianRepository.delete(musicianRepository.find(id).orElseThrow());
    }

    
    public void updatePortrait(UUID id, InputStream is) {
        musicianRepository.find(id).ifPresent(musician -> {
            try {
                musician.setPortrait(is.readAllBytes());
                musicianRepository.update(musician);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public Optional<List<Musician>> findAllByGenre(UUID id) {
        return genreRepository.find(id)
                .map(musicianRepository::findAllByGenre);
    }

    public Optional<List<Musician>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(musicianRepository::findAllByUser);
    }
}
