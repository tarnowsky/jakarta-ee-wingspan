package pl.edu.pg.eti.kask.wingspan.user.dto.function;

import pl.edu.pg.eti.kask.wingspan.user.dto.PatchUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.function.BiFunction;

public class UpdateUserWithRequestFunction implements BiFunction<User, PatchUserRequest, User> {

    @Override
    public User apply(User entity, PatchUserRequest request) {
        return User.builder()
                .id(entity.getId())
                .password(entity.getPassword())
                .login(request.getLogin())
                .avatarPath(request.getAvatarPath())
                .birthdate(request.getBirthdate())
                .build();
    }
}
