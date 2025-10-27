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

/**
 * Service layer for all business actions regarding musician entity.
 */
@ApplicationScoped
@NoArgsConstructor(force = true)
public class MusicianService {

    /**
     * Repository for musician entity.
     */
    private final MusicianRepository musicianRepository;

    /**
     * Repository for genre entity.
     */
    private final GenreRepository genreRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param musicianRepository  repository for musician entity
     * @param genreRepository repository for genre entity
     * @param userRepository repository for user entity
     */
    @Inject
    public MusicianService(MusicianRepository musicianRepository, GenreRepository genreRepository, UserRepository userRepository) {
        this.musicianRepository = musicianRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single musician.
     *
     * @param id musician's id
     * @return container with musician
     */
    public Optional<Musician> find(UUID id) {
        return musicianRepository.find(id);
    }

    /**
     * @param id   musician's id
     * @param user existing user
     * @return selected musician for user
     */
    public Optional<Musician> find(User user, UUID id) {
        return musicianRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available musicians
     */
    public List<Musician> findAll() {
        return musicianRepository.findAll();
    }

    /**
     * @param user existing user, musician's owner
     * @return all available musicians of the selected user
     */
    public List<Musician> findAll(User user) {
        return musicianRepository.findAllByUser(user);
    }

    /**
     * Creates new musician.
     *
     * @param musician new musician
     */
    public void create(Musician musician) {
        musicianRepository.create(musician);
    }

    /**
     * Updates existing musician.
     *
     * @param musician musician to be updated
     */
    public void update(Musician musician) {
        musicianRepository.update(musician);
    }

    /**
     * Deletes existing musician.
     *
     * @param id existing musician's id to be deleted
     */
    public void delete(UUID id) {
        musicianRepository.delete(musicianRepository.find(id).orElseThrow());
    }

    /**
     * Updates portrait of the musician.
     *
     * @param id musician's id
     * @param is input stream containing new portrait
     */
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
