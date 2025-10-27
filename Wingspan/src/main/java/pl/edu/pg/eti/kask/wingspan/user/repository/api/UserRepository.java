package pl.edu.pg.eti.kask.wingspan.user.repository.api;

import pl.edu.pg.eti.kask.wingspan.repository.api.Repository;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends Repository<User, UUID> {

    
    Optional<User> findByLogin(String login);

}
