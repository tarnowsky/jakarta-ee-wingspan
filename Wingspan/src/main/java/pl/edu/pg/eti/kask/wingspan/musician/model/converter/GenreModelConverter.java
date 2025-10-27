package pl.edu.pg.eti.kask.wingspan.musician.model.converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.model.GenreModel;
import pl.edu.pg.eti.kask.wingspan.musician.service.GenreService;
import pl.edu.pg.eti.kask.wingspan.component.ModelFunctionFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * Faces converter for {@link GenreModel}. The managed attribute in {@link @FacesConverter} allows the converter to
 * be the CDI bean. In previous version of JSF converters were always created inside JSF lifecycle and where not managed
 * by container that is injection was not possible. As this bean is not annotated with scope the beans.xml descriptor
 * must be present.
 */
@FacesConverter(forClass = GenreModel.class, managed = true)
public class GenreModelConverter implements Converter<GenreModel> {

    /**
     * Service for genres management.
     */
    private final GenreService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;


    /**
     * @param service service for genres management
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public GenreModelConverter(GenreService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GenreModel getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        Optional<Genre> genre = service.find(UUID.fromString(value));
        return genre.map(factory.genreToModel()).orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, GenreModel value) {
        return value == null ? "" : value.getId().toString();
    }

}
