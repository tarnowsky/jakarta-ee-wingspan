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


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Instrument implements Serializable {
    
    private UUID id;

    
    private String name;

    
    private Integer volume;

    
    private Integer resonance;

    
    private Integer toneQuality;

    
    private Integer condition;
}
