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

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusicianCreateModel {

    
    private UUID id;

    
    private String name;

    
    private String biography;

    
    private Integer age;

    
    private Integer volume;

    
    private Integer resonance;

    
    private Integer toneQuality;

    
    private Integer condition;

    
    private Integer level;

    
    private Integer experience;

    
    private Part portrait;

    
    private GenreModel genre;

}
