package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.model.GenreModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.TechniqueModel;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts {@link Genre} to {@link GenreModel}.
 */
public class GenreToModelFunction implements Function<Genre, GenreModel>, Serializable {

    /**
     * Converts {@link pl.edu.pg.eti.kask.wingspan.musician.entity.Technique} to {@link TechniqueModel}.
     */
    private final TechniqueToModelFunction techniqueToModelFunction;

    /**
     * @param techniqueToModelFunction converts {@link pl.edu.pg.eti.kask.wingspan.musician.entity.Technique} to {@link TechniqueModel}
     */
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
