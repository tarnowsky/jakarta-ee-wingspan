package pl.edu.pg.eti.kask.wingspan.user.dto.function;

import pl.edu.pg.eti.kask.wingspan.user.dto.GetUserResponse;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .birthdate(user.getBirthdate())
                .avatarPath(user.getAvatarPath())
                .build();
    }
}
