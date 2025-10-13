package pl.edu.pg.eti.kask.wingspan.bird.controller.api;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionsResponse;

/**
 * Controller for managing collections actions' representations.
 */
public interface ActionController {

    /**
     * @return all actions representation
     */
    GetActionsResponse getActions();

}

