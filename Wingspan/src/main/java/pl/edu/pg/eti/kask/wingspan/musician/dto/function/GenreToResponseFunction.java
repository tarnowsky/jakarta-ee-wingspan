package pl.edu.pg.eti.kask.wingspan.musician.dto.function;

import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenreResponse;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Converts {@link Genre} to {@link GetGenreResponse}.
 */
public class GenreToResponseFunction implements Function<Genre, GetGenreResponse> {

    @Override
    public GetGenreResponse apply(Genre entity) {
        return GetGenreResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .techniques(entity.getTechniques().entrySet().stream()
                        .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> GetGenreResponse.Technique.builder()
                                                .id(entry.getValue().getId())
                                                .name(entry.getValue().getDescription())
                                                .description(entry.getValue().getDescription())
                                                .build()
                                )
                        ))
                .build();
    }

}
