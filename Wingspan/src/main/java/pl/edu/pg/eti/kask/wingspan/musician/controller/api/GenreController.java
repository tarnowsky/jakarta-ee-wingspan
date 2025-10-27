package pl.edu.pg.eti.kask.wingspan.musician.controller.api;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenresResponse;

/**
 * Controller for managing collections genres' representations.
 */
public interface GenreController {

    /**
     * @return all genres representation
     */
    GetGenresResponse getGenres();

}
