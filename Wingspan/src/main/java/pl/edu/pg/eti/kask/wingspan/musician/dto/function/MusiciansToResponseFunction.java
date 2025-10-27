package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusiciansResponse;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;

import java.util.List;
import java.util.function.Function;


public class MusiciansToResponseFunction implements Function<List<Musician>, GetMusiciansResponse> {

    @Override
    public GetMusiciansResponse apply(List<Musician> entities) {
        return GetMusiciansResponse.builder()
                .musicians(entities.stream()
                        .map(musician -> GetMusiciansResponse.Musician.builder()
                                .id(musician.getId())
                                .name(musician.getName())
                                .build())
                        .toList())
                .build();
    }

}
