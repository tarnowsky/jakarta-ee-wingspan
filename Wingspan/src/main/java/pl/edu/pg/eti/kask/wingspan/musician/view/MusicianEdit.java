package pl.edu.pg.eti.kask.wingspan.musician.view;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianEditModel;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.component.ModelFunctionFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

/**
 * View bean for rendering single musician edit form.
 */
@ViewScoped
@Named
public class MusicianEdit implements Serializable {

    /**
     * Service for managing musicians.
     */
    private final MusicianService service;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Musician id.
     */
    @Setter
    @Getter
    private UUID id;

    /**
     * Musician exposed to the view.
     */
    @Getter
    private MusicianEditModel musician;


    /**
     * @param service service for managing musicians
     * @param factory factory producing functions for conversion between models and entities
     */
    @Inject
    public MusicianEdit(MusicianService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view.
     */
    public void init() throws IOException {
        Optional<Musician> musician = service.find(id);
        if (musician.isPresent()) {
            this.musician = factory.musicianToEditModel().apply(musician.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Musician not found");
        }
    }

    /**
     * Action initiated by clicking save button.
     *
     * @return navigation case to the same page
     */
    public String saveAction() {
        service.update(factory.updateMusician().apply(service.find(id).orElseThrow(), musician));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}
