package pl.edu.pg.eti.kask.wingspan.musician.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * GET musician response. It contains all field that can be presented (but not necessarily changed) to the used. How
 * musician is described is defined in {@link GetMusiciansResponse.Musician}
 * and {@link pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMusicianResponse {

    /**
     * Represents single genre.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Genre {

        /**
         * Unique id identifying genre.
         */
        private UUID id;

        /**
         * Name of the genre.
         */
        private String name;

    }

    /**
     * Unique id identifying musician.
     */
    private UUID id;

    /**
     * Name of the musician.
     */
    private String name;

    /**
     * Musician's biography story.
     */
    private String biography;

    /**
     * Musician's age.
     */
    private Integer age;

    /**
     * Musician's volume.
     */
    private Integer volume;

    /**
     * Musician's resonance.
     */
    private Integer resonance;

    /**
     * Musician's toneQuality.
     */
    private Integer toneQuality;

    /**
     * Musician's condition.
     */
    private Integer condition;

    /**
     * Musician's level.
     */
    private Integer level;

    /**
     * Musician's total experience.
     */
    private Integer experience;

    /**
     * Musician's genre.
     */
    private Genre genre;

}
