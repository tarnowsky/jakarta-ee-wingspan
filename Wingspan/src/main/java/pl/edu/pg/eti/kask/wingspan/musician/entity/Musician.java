package pl.edu.pg.eti.kask.wingspan.musician.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument;
import pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

/**
 * Entity for game musician owned by the user. Represents musicians basic stats (see {@link Instrument}) as well as
 * genre and techniques. Also contains link to user (see @link {@link User}) for the sake of database relationship.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class Musician extends Instrument {

    /**
     * Musician's biography story.
     */
    private String biography;

    /**
     * Musician's age.
     */
    private Integer age;

    /**
     * Musician's genre (class).
     */
    private Genre genre;

    /**
     * Owner of this musician.
     */
    private User user;

    /**
     * Musician's level.
     */
    private Integer level;

    /**
     * Musician's total experience.
     */
    private Integer experience;

    /**
     * Musician's portrait. Images in database are stored as blobs (binary large objects).
     */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] portrait;

}