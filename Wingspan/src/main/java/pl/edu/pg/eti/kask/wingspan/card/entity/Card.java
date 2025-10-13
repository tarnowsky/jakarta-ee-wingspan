package pl.edu.pg.eti.kask.wingspan.card.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Card entity for the Wingspan game.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Card implements Serializable {

    /** Unique card identifier */
    private UUID id;

    /** Point value of the card */
    private Integer points;


    /** Environment where card can be placed */
    private Environment environment;

    /** Food required for the card to be placed */
    private Food food;
}
