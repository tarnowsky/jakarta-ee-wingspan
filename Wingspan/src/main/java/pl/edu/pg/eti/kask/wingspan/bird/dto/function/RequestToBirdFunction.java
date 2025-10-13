package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.PutBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToBirdFunction implements BiFunction<UUID, PutBirdRequest, Bird> {

    @Override
    public Bird apply(UUID id, PutBirdRequest request) {
        return Bird.builder()
                .id(id)
                .name(request.getName())
                .points(request.getPoints())
                .environment(request.getEnvironment())
                .food(request.getFood())
                .maxEggNumber(request.getMaxEggNumber())
                .wingspan(request.getWingspan())
                .action(Action.builder()
                        .id(request.getAction())
                        .build())
                .illustration(request.getIllustration())
                .build();
    }
}
