package pl.edu.pg.eti.kask.wingspan.user.dto.function;

import pl.edu.pg.eti.kask.wingspan.user.dto.PutUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;


public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {

    @Override
    public User apply(UUID id, PutUserRequest request) {
        return User.builder()
                .id(id)
                .login(request.getLogin())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

}
