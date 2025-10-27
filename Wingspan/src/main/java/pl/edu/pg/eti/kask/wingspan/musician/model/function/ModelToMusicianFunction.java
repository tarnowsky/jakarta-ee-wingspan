package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import lombok.SneakyThrows;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianCreateModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link MusicianCreateModel} to {@link Musician}.
 */
public class ModelToMusicianFunction implements Function<MusicianCreateModel, Musician>, Serializable {

    @Override
    @SneakyThrows
    public Musician apply(MusicianCreateModel model) {
        return Musician.builder()
                .id(model.getId())
                .name(model.getName())
                .age(model.getAge())
                .biography(model.getBiography())
                .toneQuality(model.getToneQuality())
                .resonance(model.getResonance())
                .volume(model.getVolume())
                .experience(model.getExperience())
                .condition(model.getCondition())
                .level(model.getLevel())
                .genre(Genre.builder()
                        .id(model.getGenre().getId())
                        .build())
                .portrait(model.getPortrait() != null
                        ? model.getPortrait().getInputStream().readAllBytes()
                        : null)
                .build();
    }

}
