package pl.edu.pg.eti.kask.wingspan.musician.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JSF view model class in order to not use entity classes. Represents single musician to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusicianModel {

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
    private int age;

    /**
     * Instrument's volume.
     */
    private int volume;

    /**
     * Instrument's resonance.
     */
    private int resonance;

    /**
     * Instrument's toneQuality.
     */
    private int toneQuality;

    /**
     * Instrument's condition.
     */
    private int condition;

    /**
     * Musician's level.
     */
    private int level;

    /**
     * Musician's total experience.
     */
    private int experience;

    /**
     * Name of the musician's genre.
     */
    private String genre;

}
