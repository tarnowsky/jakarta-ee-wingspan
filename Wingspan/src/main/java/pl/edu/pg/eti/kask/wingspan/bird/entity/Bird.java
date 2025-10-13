package pl.edu.pg.eti.kask.wingspan.bird.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.wingspan.card.entity.Card;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

/**
 * Bird entity representing a bird card in a player's tableau. Extends basic {@link Card} fields as well as {@link Action} that could be performed when bird is activated. Also contains link to the {@link User} for the sake of the database relationship.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Bird extends Card {
    /** Bird name */
    private String name;

    /** Special action triggered when bird is activated */
    private Action action;

    /** Maximum number of eggs this bird can hold */
    private Integer maxEggNumber;

    /** Current number of eggs on this bird */
    private Integer numOfEggs;

    /** Number of food tokens placed on this bird */
    private Integer numOfFoodOnCard;

    /** Number of cards tucked under this bird */
    private Integer numOfCardsUnderCard;

    /** Player who owns this bird */
    private User user;

    /** Wingspan in centimeters */
    private Integer wingspan;

    /** Bird illustration image data */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] illustration;
}
