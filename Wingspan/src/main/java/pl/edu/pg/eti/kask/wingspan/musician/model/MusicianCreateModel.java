package pl.edu.pg.eti.kask.wingspan.musician.model;

import jakarta.servlet.http.Part;
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
 * JSF view model class in order to not use entity classes. Represents new musician to be created. Includes oll
 * fields which can be used in musician creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusicianCreateModel {

    /**
     * Musician's id.
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
     * Multipart part for uploaded portrait file.
     */
    private Part portrait;

    /**
     * Musician's genre.
     */
    private GenreModel genre;

}
