package pl.edu.pg.eti.kask.wingspan.bird.dto.function;

import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;

import java.util.List;
import java.util.function.Function;

public class BirdsToResponseFunction implements Function<List<Bird>, GetBirdsResponse> {

    @Override
    public GetBirdsResponse apply(List<Bird> entities) {
        return GetBirdsResponse.builder()
                .birds(entities.stream()
                        .map(bird -> GetBirdsResponse.Bird.builder()
                                .id(bird.getId())
                                .name(bird.getName())
                                .build())
                        .toList())
                .build();
    }
}
