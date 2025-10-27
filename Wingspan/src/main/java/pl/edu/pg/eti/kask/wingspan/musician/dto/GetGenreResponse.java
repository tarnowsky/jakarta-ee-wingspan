package pl.edu.pg.eti.kask.wingspan.musician.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

/**
 * GET genre response. Described details about selected genre. Can be used to present description while
 * musician creation or on musician's stat page. How genre is described is defined in
 * {@link pl.edu.pg.eti.kask.wingspan.musician.entity.Genre}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGenreResponse {

    /**
     * Describes single technique. Returning genre description without techniques list would not give all required
     * information. Forcing to return list of techniques in separate request would be unnecessary transfer growth. How
     * techniques are described is defined in {@link pl.edu.pg.eti.kask.wingspan.musician.entity.Technique}.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Technique {

        /**
         * Unique id identifying technique.
         */
        private UUID id;

        /**
         * Name of the technique.
         */
        private String name;

        /**
         * Description of the technique.
         */
        private String description;

    }

    /**
     * Unique id identifying genre.
     */
    private UUID id;

    /**
     * Name of the genre.
     */
    private String name;

    /**
     * Set of techniques available to this genre on different levels.
     */
    @Singular
    private Map<Integer, Technique> techniques;

}
