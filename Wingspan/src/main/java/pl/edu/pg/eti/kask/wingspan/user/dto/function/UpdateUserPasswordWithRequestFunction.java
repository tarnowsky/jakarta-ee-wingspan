package pl.edu.pg.eti.kask.wingspan.user.dto.function;

import pl.edu.pg.eti.kask.wingspan.user.dto.PutPasswordRequest;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.function.BiFunction;

public class UpdateUserPasswordWithRequestFunction implements BiFunction<User, PutPasswordRequest, User> {

    @Override
    public User apply(User entity, PutPasswordRequest request) {
        return User.builder()
                .id(entity.getId())
                .avatarPath(entity.getAvatarPath())
                .login(entity.getLogin())
                .password(request.getPassword())
                .birthdate(entity.getBirthdate())
                .build();
    }
}
