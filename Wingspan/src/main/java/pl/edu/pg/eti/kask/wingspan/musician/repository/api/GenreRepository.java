package pl.edu.pg.eti.kask.wingspan.musician.repository.api;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.repository.api.Repository;

import java.util.UUID;

/**
 * Repository for genre entity. Repositories should be used in business layer (e.g.: in services).
 */
public interface GenreRepository extends Repository<Genre, UUID> {

}
