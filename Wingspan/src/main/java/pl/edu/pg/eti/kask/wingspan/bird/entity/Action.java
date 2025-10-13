package pl.edu.pg.eti.kask.wingspan.bird.entity;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * Action entity representing a special ability or effect of a bird card.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Action implements Serializable {
    /** Unique action identifier */
    private UUID id;

    /** Type of action this represents */
    private ActionType actionType;

    /** Human-readable description of the action effect */
    private String description;
}
