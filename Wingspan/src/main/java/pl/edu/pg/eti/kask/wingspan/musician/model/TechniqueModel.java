package pl.edu.pg.eti.kask.wingspan.musician.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * JSF view model class in order to not use entity classes. Represents single technique to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TechniqueModel {

    /**
     * Name of the technique.
     */
    private String name;

    /**
     * Flavour text description for users.
     */
    private String description;

}
