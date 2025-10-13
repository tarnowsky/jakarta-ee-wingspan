package pl.edu.pg.eti.kask.wingspan.user.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object for updating user information via PUT requests.
 *
 * <p>This DTO contains all the fields that can be updated for a user entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PutUserRequest {
    /** User's login name */
    private String login;

    /** User's password */
    private String password;

    /** User's avatar image path */
    private String avatarPath;

    /** User's birthdate */
    private LocalDate birthdate;
}
