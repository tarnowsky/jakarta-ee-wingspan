package pl.edu.pg.eti.kask.wingspan.musician.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents list of musicians to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusiciansModel implements Serializable {

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
