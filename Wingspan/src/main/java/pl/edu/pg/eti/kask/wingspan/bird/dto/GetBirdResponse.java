package pl.edu.pg.eti.kask.wingspan.bird.dto;

import lombok.*;
import pl.edu.pg.eti.kask.wingspan.bird.entity.ActionType;
import pl.edu.pg.eti.kask.wingspan.card.entity.Environment;
import pl.edu.pg.eti.kask.wingspan.card.entity.Food;

import java.util.UUID;

/**
 * GET bird response. It contains all field that can be presented (but not necessarily changed) to the used. How
 * bird is described is defined in {@link pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdResponse}
 * and {@link pl.edu.pg.eti.kask.wingspan.card.entity.Card} classes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBirdResponse {

    /**
     * Represents single action.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Action {

        private UUID id;

        private ActionType actionType;
    }

    /**
     * Unique id identifying bird.
     */
    private UUID id;

    /**
     * Name of the bird.
     */
    private String name;

    private Integer points;

    private Environment environment;

    private Food food;

    private Integer maxEggNumber;

    private Integer numOfEggs;

    private Integer numOfFoodOnCard;

    private Integer numOfCardsUnderCard;

    private Integer wingspan;

    private Action action;

}

