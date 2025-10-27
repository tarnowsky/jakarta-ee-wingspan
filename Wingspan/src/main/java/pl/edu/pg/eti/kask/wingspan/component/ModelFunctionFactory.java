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

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class ModelFunctionFactory {

    /**
     * Returns a function to convert a single {@link Musician} to {@link MusicianModel}.
     *
     * @return new instance
     */
    public MusicianToModelFunction musicianToModel() {
        return new MusicianToModelFunction();
    }

    /**
     * Returns a function to convert a list of {@link Musician} to {@link MusiciansModel}.
     *
     * @return new instance
     */
    public MusiciansToModelFunction musiciansToModel() {
        return new MusiciansToModelFunction();
    }

    /**
     * Returns a function to convert a single {@link Musician} to {@link MusicianEditModel}.
     *
     * @return new instance
     */
    public MusicianToEditModelFunction musicianToEditModel() {
        return new MusicianToEditModelFunction();
    }

    /**
     * Returns a function to convert a single {@link MusicianModel} to {@link Musician}.
     *
     * @return new instance
     */
    public ModelToMusicianFunction modelToMusician() {
        return new ModelToMusicianFunction();
    }


    /**
     * Returns a function to convert a single {@link Genre} to {@link GenreModel}.
     *
     * @return new instance
     */
    public GenreToModelFunction genreToModel() {
        return new GenreToModelFunction(techniqueToModel());
    }

    /**
     * Returns a function to convert a single {@link Technique} to
     * {@link pl.edu.pg.eti.kask.wingspan.musician.model.TechniqueModel}.
     *
     * @return new instance
     */
    public TechniqueToModelFunction techniqueToModel() {
        return new TechniqueToModelFunction();
    }

    /**
     * Returns a function to update a {@link Musician}.
     *
     * @return UpdateMusicianFunction instance
     */
    public UpdateMusicianWithModelFunction updateMusician() {
        return new UpdateMusicianWithModelFunction();
    }

}
