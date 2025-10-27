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


@ViewScoped
@Named
public class MusicianEdit implements Serializable {

    
    private final MusicianService service;

    
    private final ModelFunctionFactory factory;

    
    @Setter
    @Getter
    private UUID id;

    
    @Getter
    private MusicianEditModel musician;


    
    @Inject
    public MusicianEdit(MusicianService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    
    public void init() throws IOException {
        Optional<Musician> musician = service.find(id);
        if (musician.isPresent()) {
            this.musician = factory.musicianToEditModel().apply(musician.get());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().responseSendError(HttpServletResponse.SC_NOT_FOUND, "Musician not found");
        }
    }

    
    public String saveAction() {
        service.update(factory.updateMusician().apply(service.find(id).orElseThrow(), musician));
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true&includeViewParams=true";
    }

}
