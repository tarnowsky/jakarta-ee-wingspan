package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.PatchBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;

import java.util.function.BiFunction;

public class UpdateBirdWithRequestFunction implements BiFunction<Bird, PatchBirdRequest, Bird> {

    @Override
    public Bird apply(Bird entity, PatchBirdRequest request) {
        return Bird.builder()
                .id(entity.getId())
                .name(entity.getName())
                .points(entity.getPoints())
                .environment(entity.getEnvironment())
                .food(entity.getFood())
                .maxEggNumber(entity.getMaxEggNumber())
                .numOfEggs(request.getNumOfEggs())
                .numOfFoodOnCard(request.getNumOfFoodOnCard())
                .numOfCardsUnderCard(request.getNumOfCardsUnderCard())
                .wingspan(entity.getWingspan())
                .action(entity.getAction())
                .illustration(entity.getIllustration())
                .build();
    }
}
