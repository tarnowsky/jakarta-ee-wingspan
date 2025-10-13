package pl.edu.pg.eti.kask.wingspan.bird.dto;

import lombok.*;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.entity.ActionType;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class GetActionsResponse {

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @Builder
    public static class Action {
        private UUID id;

        private ActionType actionType;
    }

    private List<Action> actions;
}
