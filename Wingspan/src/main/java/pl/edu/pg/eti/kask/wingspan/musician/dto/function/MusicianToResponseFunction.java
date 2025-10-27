package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusicianResponse;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;

import java.util.function.Function;

/**
 * Converts {@link Musician} to {@link GetMusicianResponse}.
 */
public class MusicianToResponseFunction implements Function<Musician, GetMusicianResponse> {

    @Override
    public GetMusicianResponse apply(Musician entity) {
        return GetMusicianResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .biography(entity.getBiography())
                .age(entity.getAge())
                .volume(entity.getVolume())
                .resonance(entity.getResonance())
                .toneQuality(entity.getToneQuality())
                .condition(entity.getCondition())
                .level(entity.getLevel())
                .experience(entity.getExperience())
                .genre(GetMusicianResponse.Genre.builder()
                        .id(entity.getGenre().getId())
                        .name(entity.getGenre().getName())
                        .build())
                .build();
    }

}
