package pl.edu.pg.eti.kask.wingspan.bird.entity;

import lombok.ToString;

/**
 * Enum representing different types of bird card actions in the Wingspan game.
 */
@ToString
public enum ActionType {
    /** Action triggered immediately when the bird is played */
    WHEN_PLAYED,

    /** Standard action that can be activated during normal gameplay */
    WHEN_ACTIVATED,

    /** Action triggered at the end of each round */
    END_OF_THE_ROUND,

    /** Action triggered at the end of the game for scoring */
    END_OF_THE_GAME,

    /** Action that can be used once between turns */
    ONCE_BETWEEN_THE_TURNS,

    /** Action related to predator birds and hunting mechanics */
    HUNTING,

    /** Special or unique action with custom rules */
    SPECIAL,
}
