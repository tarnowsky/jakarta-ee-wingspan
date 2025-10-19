package pl.edu.pg.eti.kask.wingspan.component;

import jakarta.enterprise.context.ApplicationScoped;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetBirdsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.GetActionsResponse;
import pl.edu.pg.eti.kask.wingspan.bird.dto.PutBirdRequest;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.BirdToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.BirdsToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.ActionToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.ActionsToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.RequestToBirdFunction;
import pl.edu.pg.eti.kask.wingspan.bird.dto.function.UpdateBirdWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Bird;
import pl.edu.pg.eti.kask.wingspan.bird.entity.Action;
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
     * Returns a function to convert a single {@link Bird} to {@link GetBirdResponse}.
     *
     * @return BirdToResponseFunction instance
     */
    public BirdToResponseFunction birdToResponse() {
        return new BirdToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Bird} to {@link GetBirdsResponse}.
     *
     * @return BirdsToResponseFunction instance
     */
    public BirdsToResponseFunction birdsToResponse() {
        return new BirdsToResponseFunction();
    }

    /**
     * Returns a function to convert a single {@link Action} to {@link GetActionResponse}.
     *
     * @return ActionToResponseFunction instance
     */
    public ActionToResponseFunction actionToResponse() {
        return new ActionToResponseFunction();
    }

    /**
     * Returns a function to convert a list of {@link Action} to {@link GetActionsResponse}.
     *
     * @return ActionsToResponseFunction instance
     */
    public ActionsToResponseFunction actionsToResponse() {
        return new ActionsToResponseFunction();
    }

    /**
     * Returns a function to convert a {@link PutBirdRequest} to a {@link Bird}.
     *
     * @return RequestToBirdFunction instance
     */
    public RequestToBirdFunction requestToBird() {
        return new RequestToBirdFunction();
    }

    /**
     * Returns a function to update a {@link Bird}.
     *
     * @return UpdateBirdFunction instance
     */
    public UpdateBirdWithRequestFunction updateBird() {
        return new UpdateBirdWithRequestFunction();
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
