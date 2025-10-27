package pl.edu.pg.eti.kask.wingspan.musician.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusiciansModel;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.component.ModelFunctionFactory;

/**
 * View bean for rendering list of musicians.
 */
@RequestScoped
@Named
public class MusicianList {

    /**
     * Service for managing musicians.
     */
    private final MusicianService service;

    /**
     * Musicians list exposed to the view.
     */
    private MusiciansModel musicians;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * @param service musician service
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public MusicianList(MusicianService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached using
     * lazy getter.
     *
     * @return all musicians
     */
    public MusiciansModel getMusicians() {
        if (musicians == null) {
            musicians = factory.musiciansToModel().apply(service.findAll());
        }
        return musicians;
    }

    /**
     * Action for clicking delete action.
     *
     * @param musician musician to be removed
     * @return navigation case to list_musicians
     */
    public String deleteAction(MusiciansModel.Musician musician) {
        service.delete(musician.getId());
        return "musician_list?faces-redirect=true";
    }

}
