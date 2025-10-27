package pl.edu.pg.eti.kask.wingspan.user.dto.function;

import pl.edu.pg.eti.kask.wingspan.user.dto.GetUserResponse;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.function.Function;

/**
 * Converts {@link User} to {@link GetUserResponse}.
 */
public class UserToResponseFunction implements Function<User, GetUserResponse> {

    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

}
