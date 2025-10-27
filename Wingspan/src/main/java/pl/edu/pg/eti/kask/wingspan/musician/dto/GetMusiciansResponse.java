package pl.edu.pg.eti.kask.wingspan.musician.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

/**
 * GET musicians response. Contains list of available musicians. Can be used to list particular user's musicians as
 * well as all musicians in the game.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetMusiciansResponse {

    /**
     * Represents single musician in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Musician {

        /**
         * Unique id identifying musician.
         */
        private UUID id;

        /**
         * Name of the musician.
         */
        private String name;

    }

    /**
     * Name of the selected musicians.
     */
    @Singular
    private List<Musician> musicians;

}
