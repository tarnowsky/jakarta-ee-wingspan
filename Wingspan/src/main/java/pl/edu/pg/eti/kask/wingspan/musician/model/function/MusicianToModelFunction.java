package pl.edu.pg.eti.kask.wingspan.musician.model.function;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianModel;

import java.io.Serializable;
import java.util.function.Function;


public class MusicianToModelFunction implements Function<Musician, MusicianModel>, Serializable {

    @Override
    public MusicianModel apply(Musician entity) {
        return MusicianModel.builder()
                .name(entity.getName())
                .age(entity.getAge())
                .biography(entity.getBiography())
                .toneQuality(entity.getToneQuality())
                .resonance(entity.getResonance())
                .volume(entity.getVolume())
                .experience(entity.getExperience())
                .condition(entity.getCondition())
                .level(entity.getLevel())
                .genre(entity.getGenre().getName())
                .build();
    }

}
