package pl.edu.pg.eti.kask.wingspan.musician.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusiciansModel;
import pl.edu.pg.eti.kask.wingspan.musician.service.MusicianService;
import pl.edu.pg.eti.kask.wingspan.component.ModelFunctionFactory;


@RequestScoped
@Named
public class MusicianList {

    
    private final MusicianService service;

    
    private MusiciansModel musicians;

    
    private final ModelFunctionFactory factory;

    
    @Inject
    public MusicianList(MusicianService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    
    public MusiciansModel getMusicians() {
        if (musicians == null) {
            musicians = factory.musiciansToModel().apply(service.findAll());
        }
        return musicians;
    }

    
    public String deleteAction(MusiciansModel.Musician musician) {
        service.delete(musician.getId());
        return "musician_list?faces-redirect=true";
    }

}
