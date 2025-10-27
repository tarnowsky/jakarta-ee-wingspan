package pl.edu.pg.eti.kask.wingspan.musician.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PATCH musician request. Contains all fields that can be updated by the user. How musician is described is defined
 * in {@link GetMusiciansResponse.Musician} and
 * {@link pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument} classes.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PatchMusicianRequest {

    /**
     * Musician's name.
     */
    private String name;

    /**
     * Musician's biography story.
     */
    private String biography;

    /**
     * Musician's name.
     */
    private Integer age;

}
