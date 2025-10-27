package pl.edu.pg.eti.kask.wingspan.musician.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument;
import pl.edu.pg.eti.kask.wingspan.instrument.entity.Instrument;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class Musician extends Instrument {

    
    private String biography;

    
    private Integer age;

    
    private Genre genre;

    
    private User user;

    
    private Integer level;

    
    private Integer experience;

    
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] portrait;

}