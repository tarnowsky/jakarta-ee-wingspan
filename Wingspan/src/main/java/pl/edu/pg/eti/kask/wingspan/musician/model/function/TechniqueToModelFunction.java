package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Technique;
import pl.edu.pg.eti.kask.wingspan.musician.model.TechniqueModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link Technique} to {@link TechniqueModel}.
 */
public class TechniqueToModelFunction implements Function<Technique, TechniqueModel>, Serializable {

    @Override
    public TechniqueModel apply(Technique entity) {
        return TechniqueModel.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

}
