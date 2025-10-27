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
 * PUT musician request. Contains only fields that can be set up byt the user while creating a new musician.How
 * musician is described is defined in {@link GetMusiciansResponse.Musician} and
 * {@link pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutMusicianRequest {

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
     * Musician's actual condition.
     */
    private Integer condition;

    /**
     * Identifier of the musician's genre.
     */
    private UUID genre;

}
