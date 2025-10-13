package pl.edu.pg.eti.kask.wingspan.bird.service;

import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.BirdRepository;
import pl.edu.pg.eti.kask.wingspan.bird.repository.api.ActionRepository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.repository.api.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service layer for all business actions regarding bird entity.
 */
public class BirdService {

    /**
     * Repository for bird entity.
     */
    private final BirdRepository birdRepository;

    /**
     * Repository for action entity.
     */
    private final ActionRepository actionRepository;

    /**
     * Repository for user entity.
     */
    private final UserRepository userRepository;

    /**
     * @param birdRepository  repository for bird entity
     * @param actionRepository repository for action entity
     * @param userRepository repository for user entity
     */
    public BirdService(BirdRepository birdRepository, ActionRepository actionRepository, UserRepository userRepository) {
        this.birdRepository = birdRepository;
        this.actionRepository = actionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds single bird.
     *
     * @param id bird's id
     * @return container with bird
     */
    public Optional<Bird> find(UUID id) {
        return birdRepository.find(id);
    }

    /**
     * @param id   bird's id
     * @param user existing user
     * @return selected bird for user
     */
    public Optional<Bird> find(User user, UUID id) {
        return birdRepository.findByIdAndUser(id, user);
    }

    /**
     * @return all available birds
     */
    public List<Bird> findAll() {
        return birdRepository.findAll();
    }

    /**
     * @param user existing user, bird's owner
     * @return all available birds of the selected user
     */
    public List<Bird> findAll(User user) {
        return birdRepository.findAllByUser(user);
    }

    /**
     * Creates new bird.
     *
     * @param bird new bird
     */
    public void create(Bird bird) {
        birdRepository.create(bird);
    }

    /**
     * Updates existing bird.
     *
     * @param bird bird to be updated
     */
    public void update(Bird bird) {
        birdRepository.update(bird);
    }

    /**
     * Deletes existing bird.
     *
     * @param id existing bird's id to be deleted
     */
    public void delete(UUID id) {
        birdRepository.delete(birdRepository.find(id).orElseThrow());
    }

    /**
     * Updates illustration of the bird.
     *
     * @param id bird's id
     * @param is input stream containing new illustration
     */
    public void updateIllustration(UUID id, InputStream is) {
        birdRepository.find(id).ifPresent(bird -> {
            try {
                bird.setIllustration(is.readAllBytes());
                birdRepository.update(bird);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public Optional<List<Bird>> findAllByAction(UUID id) {
        return actionRepository.find(id)
                .map(birdRepository::findAllByAction);
    }

    public Optional<List<Bird>> findAllByUser(UUID id) {
        return userRepository.find(id)
                .map(birdRepository::findAllByUser);
    }
}

