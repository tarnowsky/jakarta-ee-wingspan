package pl.edu.pg.eti.kask.wingspan.musician.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.musician.controller.api.GenreController;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenresResponse;
import pl.edu.pg.eti.kask.wingspan.musician.service.GenreService;
import pl.edu.pg.eti.kask.wingspan.component.DtoFunctionFactory;

/**
 * Simple framework agnostic implementation of controller.
 */
@RequestScoped
public class GenreSimpleController implements GenreController {

    /**
     * Genre service.
     */
    private final GenreService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;


    /**
     * @param service genre service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public GenreSimpleController(GenreService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetGenresResponse getGenres() {
        return factory.genresToResponse().apply(service.findAll());
    }

}
