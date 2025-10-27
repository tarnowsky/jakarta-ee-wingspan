package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.model.GenreModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.TechniqueModel;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class GenreToModelFunction implements Function<Genre, GenreModel>, Serializable {

    
    private final TechniqueToModelFunction techniqueToModelFunction;

    
    public GenreToModelFunction(TechniqueToModelFunction techniqueToModelFunction) {
        this.techniqueToModelFunction = techniqueToModelFunction;
    }

    @Override
    public GenreModel apply(Genre entity) {
        return GenreModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .techniques(entity.getTechniques().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> techniqueToModelFunction.apply(entry.getValue())
                        )))
                .build();
    }

}
