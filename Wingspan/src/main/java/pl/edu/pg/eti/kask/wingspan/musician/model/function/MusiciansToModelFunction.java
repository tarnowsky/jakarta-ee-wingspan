package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusiciansModel;

import java.util.List;
import java.util.function.Function;


public class MusiciansToModelFunction implements Function<List<Musician>, MusiciansModel> {

    @Override
    public MusiciansModel apply(List<Musician> entity) {
        return MusiciansModel.builder()
                .musicians(entity.stream()
                        .map(musician -> MusiciansModel.Musician.builder()
                                .id(musician.getId())
                                .name(musician.getName())
                                .build())
                        .toList())
                .build();
    }

}
