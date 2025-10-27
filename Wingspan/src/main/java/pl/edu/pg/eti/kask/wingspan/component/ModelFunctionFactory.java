package pl.edu.pg.eti.kask.wingspan.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenreResponse.Technique;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianEditModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusicianModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.MusiciansModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.GenreModel;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.MusicianToEditModelFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.MusicianToModelFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.MusiciansToModelFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.ModelToMusicianFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.GenreToModelFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.TechniqueToModelFunction;
import pl.edu.pg.eti.kask.wingspan.musician.model.function.UpdateMusicianWithModelFunction;

import java.util.function.Function;


@ApplicationScoped
public class ModelFunctionFactory {

    
    public MusicianToModelFunction musicianToModel() {
        return new MusicianToModelFunction();
    }

    
    public MusiciansToModelFunction musiciansToModel() {
        return new MusiciansToModelFunction();
    }

    
    public MusicianToEditModelFunction musicianToEditModel() {
        return new MusicianToEditModelFunction();
    }

    
    public ModelToMusicianFunction modelToMusician() {
        return new ModelToMusicianFunction();
    }


    
    public GenreToModelFunction genreToModel() {
        return new GenreToModelFunction(techniqueToModel());
    }

    
    public TechniqueToModelFunction techniqueToModel() {
        return new TechniqueToModelFunction();
    }

    
    public UpdateMusicianWithModelFunction updateMusician() {
        return new UpdateMusicianWithModelFunction();
    }

}
