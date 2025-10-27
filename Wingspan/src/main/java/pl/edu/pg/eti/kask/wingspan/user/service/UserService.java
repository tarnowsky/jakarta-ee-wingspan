package pl.edu.pg.eti.kask.wingspan.user.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import pl.edu.pg.eti.kask.wingspan.crypto.component.Pbkdf2PasswordHash;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;
import pl.edu.pg.eti.kask.wingspan.user.repository.api.UserRepository;

import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {

    
    private final UserRepository repository;

    
    private final Pbkdf2PasswordHash passwordHash;

    
    @Inject
    public UserService(UserRepository repository, Pbkdf2PasswordHash passwordHash) {
        this.repository = repository;
        this.passwordHash = passwordHash;
    }

    
    public Optional<User> find(UUID id) {
        return repository.find(id);
    }

    
    public Optional<User> find(String login) {
        return repository.findByLogin(login);
    }

    
    public void create(User user) {
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        repository.create(user);
    }

    
    public boolean verify(String login, String password) {
        return find(login)
                .map(user -> passwordHash.verify(password.toCharArray(), user.getPassword()))
                .orElse(false);
    }

}
