package pl.edu.pg.eti.kask.wingspan.card.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Class representing food requirements for bird cards.
 * Contains different types of food that birds can consume in the game.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@SuperBuilder
public class Food implements Serializable {
    /**
     * Number of worms required.
     */
    private Integer worms;

    /**
     * Number of fruits required.
     */
    private Integer fruits;

    /**
     * Number of rodents required.
     */
    private Integer rodents;

    /**
     * Number of plants required.
     */
    private Integer plants;

    /**
     * Number of fish required.
     */
    private Integer fish;

    /**
     * Number of any type of food required or available (wildcard food).
     */
    private Integer any;
}
