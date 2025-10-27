package pl.edu.pg.eti.kask.wingspan.musician.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents single genre to be displayed or selected.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GenreModel {

    /**
     * Genre's id.
     */
    private UUID id;

    /**
     * Name of the genre.
     */
    private String name;

    /**
     * Set of techniques available on different levels. While leveling up, musician gains access to new techniques. One technique
     * every limit level. There is no rule which levels are limit ones.
     */
    private Map<Integer, TechniqueModel> techniques;

}
