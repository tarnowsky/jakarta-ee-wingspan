package pl.edu.pg.eti.kask.wingspan.bird.dto;

import lombok.*;
import pl.edu.pg.eti.kask.wingspan.card.entity.Environment;
import pl.edu.pg.eti.kask.wingspan.card.entity.Food;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutBirdRequest {

    private Integer points;

    private Environment environment;

    private Food food;

    /** Bird name */
    private String name;

    /** Special action triggered when bird is activated */
    private UUID action;

    /** Maximum number of eggs this bird can hold */
    private Integer maxEggNumber;

    /** Wingspan in centimeters */
    private Integer wingspan;

    /** Bird illustration image data */
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] illustration;
}
