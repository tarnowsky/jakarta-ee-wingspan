package pl.edu.pg.eti.kask.wingspan.repository.api;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository interface for CRUD operations on entities.
 *
 * @param <E> the entity type
 * @param <K> the key/identifier type
 */
public interface Repository<E, K> {

    /**
     * Finds an entity by its identifier.
     *
     * @param id the unique identifier of the entity
     * @return an Optional containing the entity if found, empty otherwise
     */
    Optional<E> find(K id);

    /**
     * Retrieves all entities from the repository.
     *
     * @return a list of all entities
     */
    List<E> findAll();

    /**
     * Creates a new entity in the repository.
     *
     * @param entity the entity to create
     */
    void create(E entity);

    /**
     * Deletes an entity from the repository.
     *
     * @param entity the entity to delete
     */
    void delete(E entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity the entity to update
     */
    void update(E entity);
}
