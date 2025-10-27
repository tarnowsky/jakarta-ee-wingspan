package pl.edu.pg.eti.kask.wingspan.musician.model;

import jakarta.servlet.http.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JSF view model class in order to not use entity classes. Represents single musician to be edited. Includes
 * only fields which can be edited after musician creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusicianEditModel {

    /**
     * Name of the musician.
     */
    private String name;

    /**
     * Musician's biography story.
     */
    private String biography;

    /**
     * Musician's age.
     */
    private int age;

    /**
     * Multipart part for uploaded portrait file.
     */
    private Part portrait;

}
