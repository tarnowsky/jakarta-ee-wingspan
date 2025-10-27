package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenresResponse;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;

import java.util.List;
import java.util.function.Function;

/**
 * Converts {@link List<Genre>} to {@link GetGenresResponse}.
 */
public class GenresToResponseFunction implements Function<List<Genre>, GetGenresResponse> {

    @Override
    public GetGenresResponse apply(List<Genre> entities) {
        return GetGenresResponse.builder()
                .genres(entities.stream()
                        .map(genre -> GetGenresResponse.Genre.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .build())
                        .toList())
                .build();
    }

}
