package pl.edu.pg.eti.kask.wingspan.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for returning user information via GET requests.
 *
 * <p>This DTO contains the user fields that are safe to expose in API responses.
 * Sensitive information like passwords are excluded from this response object.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class GetUserResponse {
    /** Unique user identifier */
    private UUID id;

    /** User's login name */
    private String login;

    /** User's birthdate */
    private LocalDate birthdate;

    /** Path to user's avatar image */
    private String avatarPath;
}
