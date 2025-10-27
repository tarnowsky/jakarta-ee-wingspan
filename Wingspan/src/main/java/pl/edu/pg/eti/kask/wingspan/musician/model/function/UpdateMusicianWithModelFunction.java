package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Musician} based on provided value and updated with values from
 * {@link MusicianEditModel}.
 */
public class UpdateMusicianWithModelFunction implements BiFunction<Musician, MusicianEditModel, Musician>, Serializable {

    @Override
    @SneakyThrows
    public Musician apply(Musician entity, MusicianEditModel request) {
        return Musician.builder()
                .id(entity.getId())
                .name(request.getName())
                .biography(request.getBiography())
                .age(request.getAge())
                .volume(entity.getVolume())
                .resonance(entity.getResonance())
                .toneQuality(entity.getToneQuality())
                .condition(entity.getCondition())
                .experience(entity.getExperience())
                .level(entity.getLevel())
                .genre(entity.getGenre())
                .portrait(request.getPortrait() != null
                        ? request.getPortrait().getInputStream().readAllBytes()
                        : entity.getPortrait())
                .build();
    }

}
