package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionResponse;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;

import java.util.function.Function;

public class ActionToResponseFunction implements Function<Action, GetActionResponse> {

    @Override
    public GetActionResponse apply(Action entity) {
        return GetActionResponse.builder()
                .id(entity.getId())
                .actionType(entity.getActionType())
                .description(entity.getDescription())
                .build();
    }
}
