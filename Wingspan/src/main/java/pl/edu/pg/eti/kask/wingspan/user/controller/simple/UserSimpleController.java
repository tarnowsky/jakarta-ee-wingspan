package pl.edu.pg.eti.kask.wingspan.user.controller.simple;

import pl.edu.pg.eti.kask.wingspan.controller.servlet.exception.NotFoundException;
import pl.edu.pg.eti.kask.wingspan.user.controller.api.UserController;
import pl.edu.pg.eti.kask.wingspan.user.dto.GetUserResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.GetUsersResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.PatchUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.dto.PutUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.RequestToUserFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UpdateUserWithRequestFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UserToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.dto.function.UsersToResponseFunction;
import pl.edu.pg.eti.kask.wingspan.user.service.UserAvatarService;
import pl.edu.pg.eti.kask.wingspan.user.service.UserService;

import java.io.InputStream;
import java.util.UUID;

public class UserSimpleController implements UserController {
    private final UserService userService;
    private final UserAvatarService avatarService;
    private final UsersToResponseFunction usersToResponse;
    private final UserToResponseFunction userToResponse;
    private final RequestToUserFunction requestToUser;
    private final UpdateUserWithRequestFunction updateUserWithRequest;

    public UserSimpleController(
            UserService userService,
            UserAvatarService avatarService,
            UsersToResponseFunction usersToResponse,
            UserToResponseFunction userToResponse,
            RequestToUserFunction requestToUser,
            UpdateUserWithRequestFunction updateUserWithRequest) {
        this.userService = userService;
        this.avatarService = avatarService;
        this.usersToResponse = usersToResponse;
        this.userToResponse = userToResponse;
        this.requestToUser = requestToUser;
        this.updateUserWithRequest = updateUserWithRequest;
    }

    @Override
    public GetUsersResponse getUsers() {
        return usersToResponse.apply(userService.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID id) {
        return userService.find(id)
                .map(userToResponse)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        if (userService.find(id).isPresent()) {
            throw new IllegalArgumentException("User already exists. Use PATCH to modify.");
        }
        var user = requestToUser.apply(id, request);
        userService.create(user);
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
        userService.find(id).ifPresent(user -> {
            var updatedUser = updateUserWithRequest.apply(user, request);
            userService.update(updatedUser);
        });
    }

    @Override
    public void deleteUser(UUID id) {
        userService.find(id).ifPresent(userService::delete);
    }

    @Override
    public byte[] getUserAvatar(UUID id) {
        return userService.find(id)
                .map(user -> avatarService.getAvatar(id))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void putUserAvatar(UUID id, InputStream avatar) {
        userService.find(id).ifPresent(user -> {
            avatarService.saveAvatar(id, avatar);
            user.setAvatarPath(avatarService.getAvatarPath(id));
            userService.update(user);
        });
    }

    @Override
    public void deleteUserAvatar(UUID id) {
        userService.find(id).ifPresent(user -> {
            avatarService.deleteAvatar(id);
            user.setAvatarPath(null);
            userService.update(user);
        });
    }
}
