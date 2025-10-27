package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianEditModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link Musician} to {@link MusicianEditModel}.
 */
public class MusicianToEditModelFunction implements Function<Musician, MusicianEditModel>, Serializable {

    @Override
    public MusicianEditModel apply(Musician entity) {
        return MusicianEditModel.builder()
                .name(entity.getName())
                .age(entity.getAge())
                .biography(entity.getBiography())
                .build();
    }

}
