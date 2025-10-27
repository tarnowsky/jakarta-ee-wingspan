package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;

import java.util.UUID;
import java.util.function.BiFunction;

/**
 * Converts {@link PutMusicianRequest} to {@link Musician}. Caution, some fields are not set as they should be updated
 * by business logic.
 */
public class RequestToMusicianFunction implements BiFunction<UUID, PutMusicianRequest, Musician> {

    @Override
    public Musician apply(UUID id, PutMusicianRequest request) {
        return Musician.builder()
                .id(id)
                .name(request.getName())
                .biography(request.getBiography())
                .age(request.getAge())
                .volume(request.getVolume())
                .resonance(request.getResonance())
                .toneQuality(request.getToneQuality())
                .condition(request.getCondition())
                .genre(Genre.builder()
                        .id(request.getGenre())
                        .build())
                .build();
    }

}
