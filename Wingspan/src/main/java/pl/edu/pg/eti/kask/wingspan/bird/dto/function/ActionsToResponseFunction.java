package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;

import java.util.List;
import java.util.function.Function;

public class ActionsToResponseFunction implements Function<List<Action>, GetActionsResponse> {

    @Override
    public GetActionsResponse apply(List<Action> entities) {
        return GetActionsResponse.builder()
                .actions(entities.stream()
                        .map(action -> GetActionsResponse.Action.builder()
                                .id(action.getId())
                                .actionType(action.getActionType())
                                .build())
                        .toList())
                .build();
    }
}
