package pl.edu.pg.eti.kask.wingspan.musician.repository.api;

import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.repository.api.Repository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MusicianRepository extends Repository<Musician, UUID> {

    
    Optional<Musician> findByIdAndUser(UUID id, User user);

    
    List<Musician> findAllByUser(User user);

    
    List<Musician> findAllByGenre(Genre genre);

}
