package pl.edu.pg.eti.kask.wingspan.user.controller.api;

import pl.edu.pg.eti.kask.wingspan.user.dto.GetUserResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.GetUsersResponse;
import pl.edu.pg.eti.kask.wingspan.user.dto.PatchUserRequest;
import pl.edu.pg.eti.kask.wingspan.user.dto.PutUserRequest;

import java.io.InputStream;
import java.util.UUID;

public interface UserController {
    GetUsersResponse getUsers();
    GetUserResponse getUser(UUID id);
    void putUser(UUID id, PutUserRequest request);
    void patchUser(UUID id, PatchUserRequest request);
    void deleteUser(UUID id);
    byte[] getUserAvatar(UUID id);
    void putUserAvatar(UUID id, InputStream avatar);
    void deleteUserAvatar(UUID id);
}
