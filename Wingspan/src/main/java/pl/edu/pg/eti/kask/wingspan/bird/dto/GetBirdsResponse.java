package pl.edu.pg.eti.kask.wingspan.bird.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * GET birds response. Contains list of available birds. Can be used to list particular user's birds as
 * well as all birds in the game.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetBirdsResponse {

    /**
     * Represents single bird in list.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Bird {

        /**
         * Unique id identifying bird.
         */
        private UUID id;

        /**
         * Name of the bird.
         */
        private String name;

    }

    /**
     * Name of the selected birds.
     */
    @Singular
    private List<Bird> birds;

}

