package pl.edu.pg.eti.kask.wingspan.instrument.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * Entity for game instrument. Represents all instruments that can be found in the game as well as is base class for are
 * musician classes and possible NPCs.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Instrument implements Serializable {
    /**
     * Unique id (primary key).
     */
    private UUID id;

    /**
     * Instrument's name.
     */
    private String name;

    /**
     * Instrument's volume stat. Describes the instrument's sound power.
     */
    private Integer volume;

    /**
     * Instrument's resonance stat. Describes how resilient the instrument is to wear and use.
     */
    private Integer resonance;

    /**
     * Instrument's tone quality stat. Describes the richness and musician of its sound.
     */
    private Integer toneQuality;

    /**
     * Instrument's current condition.
     */
    private Integer condition;
}
