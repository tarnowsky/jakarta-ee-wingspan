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


@ApplicationScoped
public class DtoFunctionFactory {

    
    public MusicianToResponseFunction musicianToResponse() {
        return new MusicianToResponseFunction();
    }

    
    public MusiciansToResponseFunction musiciansToResponse() {
        return new MusiciansToResponseFunction();
    }

    
    public GenreToResponseFunction genreToResponse() {
        return new GenreToResponseFunction();
    }

    
    public GenresToResponseFunction genresToResponse() {
        return new GenresToResponseFunction();
    }

    
    public RequestToMusicianFunction requestToMusician() {
        return new RequestToMusicianFunction();
    }

    
    public UpdateMusicianWithRequestFunction updateMusician() {
        return new UpdateMusicianWithRequestFunction();
    }

    
    public RequestToUserFunction requestToUser() {
        return new RequestToUserFunction();
    }

    
    public UpdateUserWithRequestFunction updateUser() {
        return new UpdateUserWithRequestFunction();
    }

    
    public UpdateUserPasswordWithRequestFunction updateUserPassword() {
        return new UpdateUserPasswordWithRequestFunction();
    }

    
    public UsersToResponseFunction usersToResponse() {
        return new UsersToResponseFunction();
    }

    
    public UserToResponseFunction userToResponse() {
        return new UserToResponseFunction();
    }

}
