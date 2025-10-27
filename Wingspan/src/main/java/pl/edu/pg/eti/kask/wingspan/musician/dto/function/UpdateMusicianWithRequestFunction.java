package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.PatchMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;

import java.util.function.BiFunction;


public class UpdateMusicianWithRequestFunction implements BiFunction<Musician, PatchMusicianRequest, Musician> {

    @Override
    public Musician apply(Musician entity, PatchMusicianRequest request) {
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
                .portrait(entity.getPortrait())
                .build();
    }

}
