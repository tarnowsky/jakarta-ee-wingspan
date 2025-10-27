package pl.edu.pg.eti.kask.wingspan.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusicianResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetMusiciansResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenreResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.GetGenresResponse;
import pl.edu.pg.eti.kask.wingspan.musician.dto.PutMusicianRequest;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.MusicianToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.MusiciansToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.GenreToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.GenresToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.RequestToMusicianFunction;
import pl.edu.pg.eti.kask.wingspan.musician.dto.function.UpdateMusicianWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Musician;
import pl.edu.pg.eti.kask.wingspan.musician.entity.Genre;
import pl.edu.pg.eti.kask.wingspan.user.dto.GetUserResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.GetUsersResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.PutUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.RequestToUserFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UpdateUserPasswordWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UpdateUserWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UserToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UsersToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.entity.User;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class DtoFunctionFactory {

    /**
     * Returns a function to convert a single {@link Musician} to {@link GetMusicianResponse}.
     *
     * @return MusicianToResponseFunction instance
     */
    public MusicianToResponseFunction musicianToResponse() {
        return new MusicianToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Musician} to {@link GetMusiciansResponse}.
     *
     * @return MusiciansToResponseFunction instance
     */
    public MusiciansToResponseFunction musiciansToResponse() {
        return new MusiciansToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link Genre} to {@link GetGenreResponse}.
     *
     * @return GenreToResponseFunction instance
     */
    public GenreToResponseFunction genreToResponse() {
        return new GenreToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Genre} to {@link GetGenresResponse}.
     *
     * @return GenresToResponseFunction instance
     */
    public GenresToResponseFunction genresToResponse() {
        return new GenresToResponseFunction();
    }

    /**
     * Returns a function to convert a {@link PutMusicianRequest} to a {@link Musician}.
     *
     * @return RequestToMusicianFunction instance
     */
    public RequestToMusicianFunction requestToMusician() {
        return new RequestToMusicianFunction();
    }

    /**
     * Returns a function to update a {@link Musician}.
     *
     * @return UpdateMusicianFunction instance
     */
    public UpdateMusicianWithRequestFunction updateMusician() {
        return new UpdateMusicianWithRequestFunction();
    }

    /**
     * Returns a function to convert a {@link PutUserRequest} to a {@link User}.
     *
     * @return RequestToUserFunction instance
     */
    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    /**
     * Returns a function to update a {@link User}.
     *
     * @return UpdateUserFunction instance
     */
    public UpdateUserWithRequestFunction updateUser() {
        return new UpdateUserWithRequestFunction();
    }

    /**
     * Returns a function to update a {@link User}'s password.
     *
     * @return UpdateUserPasswordFunction instance
     */
    public UpdateUserPasswordWithRequestFunction updateUserPassword() {
        return new UpdateUserPasswordWithRequestFunction();
    }

    /**
     * Returns a function to convert a list of {@link User} to {@link GetUsersResponse}.
     *
     * @return UsersToResponseFunction instance
     */
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link User} to {@link GetUserResponse}.
     *
     * @return UserToResponseFunction instance
     */
    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

}
