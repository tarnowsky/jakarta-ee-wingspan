package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdResponse;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;

import java.util.function.Function;

public class BirdToResponseFunction implements Function<Bird, GetBirdResponse> {

    @Override
    public GetBirdResponse apply(Bird entity) {
        return GetBirdResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .points(entity.getPoints())
                .environment(entity.getEnvironment())
                .food(entity.getFood())
                .maxEggNumber(entity.getMaxEggNumber())
                .numOfEggs(entity.getNumOfEggs())
                .numOfFoodOnCard(entity.getNumOfFoodOnCard())
                .numOfCardsUnderCard(entity.getNumOfCardsUnderCard())
                .wingspan(entity.getWingspan())
                .action(GetBirdResponse.Action.builder()
                        .id(entity.getAction().getId())
                        .actionType(entity.getAction().getActionType())
                        .build())
                .build();
    }
}
