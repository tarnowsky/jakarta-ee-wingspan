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


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class MusicianEditModel {

    
    private String name;

    
    private String biography;

    
    private int age;

    
    private Part portrait;

}
