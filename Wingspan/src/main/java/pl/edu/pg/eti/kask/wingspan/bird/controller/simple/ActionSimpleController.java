package pl.edu.pg.eti.kask.wingspan.bird.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import pl.edu.pg.eti.kask.wingspan.bird.controller.api.ActionController;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.service.ActionService;
import pl.edu.pg.eti.kask.wingspan.component.DtoFunctionFactory;

/**
 * Simple framework agnostic implementation of controller.
 */
@RequestScoped
public class ActionSimpleController implements ActionController {

    /**
     * Action service.
     */
    private final ActionService service;

    /**
     * Factory producing functions for conversion between DTO and entities.
     */
    private final DtoFunctionFactory factory;


    /**
     * @param service action service
     * @param factory factory producing functions for conversion between DTO and entities
     */
    @Inject
    public ActionSimpleController(ActionService service, DtoFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }

    @Override
    public GetActionsResponse getActions() {
        return factory.actionsToResponse().apply(service.findAll());
    }

}

